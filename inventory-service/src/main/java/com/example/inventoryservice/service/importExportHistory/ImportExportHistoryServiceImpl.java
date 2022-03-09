package com.example.inventoryservice.service.importExportHistory;

import com.example.inventoryservice.entity.ImportExportHistory;
import com.example.inventoryservice.repositoty.ImportExportHistoryRepository;
import com.example.inventoryservice.response.ResponseApi;
import com.example.inventoryservice.specification.ObjectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportExportHistoryServiceImpl implements ImportExportHistoryService{

    @Autowired
    ImportExportHistoryRepository importExportHistoryRepository;

    @Override
    public ResponseApi findAll(ObjectFilter objectFilter) {
        PageRequest paging = PageRequest.of(objectFilter.getPage()-1, objectFilter.getPageSize());
        Page<ImportExportHistory> data = importExportHistoryRepository.findAll(paging);
        return new ResponseApi(HttpStatus.OK,"success",data);
    }

    @Override
    public ResponseApi findByProvider(int providerId) {
        List<ImportExportHistory> data = importExportHistoryRepository.findImportExportHistoriesByProviderId( providerId);
        return new ResponseApi(HttpStatus.OK,"success",data);
    }

    @Override
    public ResponseApi create(ImportExportHistory importExportHistory) {
        return new ResponseApi(HttpStatus.CREATED,"created",importExportHistoryRepository.save(importExportHistory));
    }

    @Override
    public ResponseApi delete(int id) {
        return null;
    }
}
