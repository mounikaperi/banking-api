package com.vaultsystem.customer.service;

import com.vaultsystem.customer.dto.BankDTO;

import java.util.List;

public interface UserBankMappingService {
    void registerBankIdAccountIdOfUser(String customerId, List<BankDTO> banks);
}
