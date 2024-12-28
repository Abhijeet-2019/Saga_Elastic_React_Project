package com.segaExamples.ElasticService.Dao;

import com.segaExamples.commonService.models.InventoryDetailsElastic;
import com.segaExamples.commonService.utils.ElasticException;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface ElasticDao {

    String saveDataInElasticIndex(String indexname, XContentBuilder xContentBuilder) throws ElasticException;

    List<String> searchItemBasedOnCriteria(String indexName, Map searchCriteriaMap) throws ElasticException, IOException;

    List<String> fetchAllItems(String indexName, int fetchSize, int offset) throws IOException;
}
