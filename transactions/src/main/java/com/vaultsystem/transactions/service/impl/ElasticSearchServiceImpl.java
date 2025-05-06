package com.vaultsystem.transactions.service.impl;


import com.vaultsystem.transactions.dto.TransactionDTO;
import com.vaultsystem.transactions.service.ElasticSearchService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Value("${spring.elasticsearch.index.transactions}")
    private String transactionIndex;

    public ElasticSearchServiceImpl(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    @PostConstruct
    public void initIndex() {
        IndexOperations ops = elasticsearchRestTemplate.indexOps(TransactionDTO.class);
        if (!ops.exists()) {
            ops.create();
            ops.putMapping(ops.createMapping());
        }
    }

    public void indexTransaction(TransactionDTO txnDoc) {
        IndexQuery query = new IndexQueryBuilder()
                .withId(txnDoc.getTransactionId())
                .withObject(txnDoc)
                .build();
        elasticsearchRestTemplate.index(query, IndexCoordinates.of(transactionIndex));
    }
}
