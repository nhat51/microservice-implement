package com.example.inventoryservice.repositoty;

import com.example.inventoryservice.entity.ImportExportHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImportExportHistoryRepository extends JpaRepository<ImportExportHistory,Integer> {
    List<ImportExportHistory> findImportExportHistoriesByProviderId(int providerId);
}
