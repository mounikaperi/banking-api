package com.vaultsystem.transactions.service.impl;

import com.vaultsystem.transactions.dto.CustomerBankDetailsDTO;
import com.vaultsystem.transactions.dto.TransactionDTO;
import com.vaultsystem.transactions.mappers.BankRepositoryMapper;
import com.vaultsystem.transactions.service.TransactionService;
import com.vaultsystem.transactions.service.client.CustomerFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final BankRepositoryMapper bankRepositoryMapper;
    private final CustomerFeignClient customerFeignClient;

    public TransactionServiceImpl(BankRepositoryMapper bankRepositoryMapper, CustomerFeignClient customerFeignClient) {
        this.bankRepositoryMapper = bankRepositoryMapper;
        this.customerFeignClient = customerFeignClient;
    }

    @Override
    public List<TransactionDTO> getAllTransactions(String userId) {
        List<TransactionDTO> allResponses = new ArrayList<>();
        ResponseEntity<List<CustomerBankDetailsDTO>> customerBankDetailsDTOResponseEntity = customerFeignClient.fetchAllUserBankAccounts(userId);
        if (customerBankDetailsDTOResponseEntity != null) {
            customerBankDetailsDTOResponseEntity.getBody().stream().forEach((currentBankDetails) -> {
                String bankName = currentBankDetails.getBankName();
                String bankAccountId = currentBankDetails.getBankAccountId();
                List<TransactionDTO> response = bankRepositoryMapper.fetchTransactions(bankName, bankAccountId);
                allResponses.addAll(response);
            });
        }
        return allResponses;
    }
}
