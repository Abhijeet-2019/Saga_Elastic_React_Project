package com.segaExamples.InventoryService.Repositories;

import com.segaExamples.InventoryService.model.InventoryDetailsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryDetailsTable,Long> {

    public List<InventoryDetailsTable> findByItemCode(String itemCode);


    public List<InventoryDetailsTable> findByItemCategory(String itemCategory);
}

