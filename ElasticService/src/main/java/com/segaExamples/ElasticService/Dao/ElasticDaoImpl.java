package com.segaExamples.ElasticService.Dao;

import com.segaExamples.commonService.models.InventoryDetailsElastic;
import com.segaExamples.commonService.utils.ElasticException;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ElasticDaoImpl implements ElasticDao {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public String saveDataInElasticIndex(String indexName, XContentBuilder xContentBuilder) throws ElasticException {
        IndexRequest indexRequest = new IndexRequest().index(indexName).source(xContentBuilder);
        final IndexResponse response;
        try {
            response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response.getId();
    }

    @Override
    public List<String> searchItemBasedOnCriteria(String indexName, Map searchCriteriaMap) throws ElasticException, IOException {
        log.info("Start fetching data from index based on criteria ");
        // Create a search request
        SearchRequest searchRequest = new SearchRequest(indexName);

        // Build the query
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        searchCriteriaMap.forEach((key, value) -> {
            boolQueryBuilder.must(QueryBuilders.matchQuery(key.toString(), value));
        });
        // Set the query to the search source
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQueryBuilder);
        searchRequest.source(sourceBuilder);

        // Execute the search
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        // Collect results
        List<String> results = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            results.add(hit.getSourceAsString());
        }
        return results;
    }


    @Override
    public final List<String> fetchAllItems(String indexName, int fetchSize, int offset) throws IOException {
        List<String> resultsJSONList = new ArrayList<>();
        List<InventoryDetailsElastic> fetchAllItems = new ArrayList<>();
        // Create the search request for the given index
        SearchRequest searchRequest = new SearchRequest(indexName);

        // Configure the query
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(org.elasticsearch.index.query.QueryBuilders.matchAllQuery());
        sourceBuilder.from(offset); // Offset (starting point)
        sourceBuilder.size(fetchSize); // Number of records to fetch
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS)); // Optional timeout
        sourceBuilder.sort("itemAddedDate", SortOrder.DESC);
        searchRequest.source(sourceBuilder);

        // Execute the search request
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        // Extract and return results
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            resultsJSONList.add(hit.getSourceAsString()); // Convert the document to JSON string
        }
        log.info("results" + resultsJSONList);
        return resultsJSONList;
    }
}
