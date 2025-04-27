package com.vaultsystem.customer.service.impl;

import com.vaultsystem.customer.dto.BankDTO;
import com.vaultsystem.customer.dto.UserBankMappingDTO;
import com.vaultsystem.customer.entities.UserBankMapping;
import com.vaultsystem.customer.repository.UserBankMappingRepository;
import com.vaultsystem.customer.service.UserBankMappingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserBankMappingServiceImpl implements UserBankMappingService {

    private final UserBankMappingRepository userBankMappingRepository;

    @Override
    public void registerBankIdAccountIdOfUser(String userId, List<UserBankMappingDTO> banks) {
        List<UserBankMapping> mappings = new ArrayList<>();
        for (UserBankMappingDTO bank: banks) {
            UserBankMapping mapping = new UserBankMapping(
                    userId,
                    bank.getBankId(),
                    bank.getBankAccountId(),
                    Boolean.TRUE
            );
            mappings.add(mapping);
        }
        userBankMappingRepository.saveAll(mappings);
    }
}
