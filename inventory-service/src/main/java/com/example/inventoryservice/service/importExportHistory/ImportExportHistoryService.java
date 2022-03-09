package com.example.inventoryservice.service.importExportHistory;
import com.example.inventoryservice.entity.ImportExportHistory;
import com.example.inventoryservice.response.ResponseApi;
import com.example.inventoryservice.specification.ObjectFilter;

public interface ImportExportHistoryService {
    ResponseApi findAll(ObjectFilter objectFilter);
    ResponseApi findByProvider(int providerId);
    ResponseApi create(ImportExportHistory importExportHistory);
    ResponseApi delete(int id);
}
