package com.vaultsystem.user.service;

import com.vaultsystem.user.dto.BankDTO;

import java.util.List;

public interface UserBankMappingService {
    void registerBankIdAccountIdOfUser(String customerId, List<BankDTO> banks);
}
