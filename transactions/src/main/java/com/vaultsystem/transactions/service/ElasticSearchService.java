package com.vaultsystem.transactions.service;

import com.vaultsystem.transactions.dto.TransactionDTO;

public interface ElasticSearchService {
    public void indexTransaction(TransactionDTO txnDoc);
}
