package com.vaultsystem.customer.service;

import com.vaultsystem.customer.dto.UserBankMappingDTO;

import java.util.List;

public interface UserBankMappingService {
    void registerBankIdAccountIdOfUser(String userId, List<UserBankMappingDTO> banks);
}
