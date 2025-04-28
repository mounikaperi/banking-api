package com.vaultsystem.transactions.service;

import com.vaultsystem.transactions.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    public List<TransactionDTO> getAllTransactions(String userId);
}
