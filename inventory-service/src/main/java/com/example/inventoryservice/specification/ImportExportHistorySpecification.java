package com.example.inventoryservice.specification;

import com.example.inventoryservice.entity.ImportExportHistory;
import com.example.inventoryservice.util.SQLConstant;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ImportExportHistorySpecification implements Specification<ImportExportHistory> {

    private SearchCriteria searchCriteria;

    public ImportExportHistorySpecification(SearchCriteria searchCriteria){
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<ImportExportHistory> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (searchCriteria.getOperation().equalsIgnoreCase(SQLConstant.GREATER_THAN_OR_EQUAL_TO)) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.<String> get(searchCriteria.getKey()), searchCriteria.getValue().toString());
        }
        else if (searchCriteria.getOperation().equalsIgnoreCase(SQLConstant.LESS_THAN_OR_EQUAL_TO)) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.<String> get(searchCriteria.getKey()), searchCriteria.getValue().toString());
        }
        else if (searchCriteria.getOperation().equalsIgnoreCase(SQLConstant.LIKE)) {
            if (root.get(searchCriteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.<String>get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
            }
        }else if (searchCriteria.getOperation().equalsIgnoreCase(SQLConstant.EQUAL)){
            return criteriaBuilder.equal(
                    root.<String>get(searchCriteria.getKey()), searchCriteria.getValue() );
        }
        return null;
    }
}
