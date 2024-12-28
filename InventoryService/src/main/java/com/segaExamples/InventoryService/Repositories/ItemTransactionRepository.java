package com.segaExamples.InventoryService.Repositories;


import com.segaExamples.InventoryService.model.InventoryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTransactionRepository extends JpaRepository<InventoryTransaction,Long> {


}
