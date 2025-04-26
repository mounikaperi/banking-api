package com.vaultsystem.customer.service;

import com.vaultsystem.customer.dto.BankDTO;
import com.vaultsystem.customer.model.UserBankMapping;
import com.vaultsystem.customer.repository.UserBankMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserBankMappingServiceImpl implements UserBankMappingService {

    private final UserBankMappingRepository userBankMappingRepository;

    @Override
    public void registerBankIdAccountIdOfUser(String customerId, List<BankDTO> banks) {
        List<UserBankMapping> mappings = new ArrayList<>();
        for (BankDTO bank: banks) {
            UserBankMapping mapping = new UserBankMapping(
                    customerId,
                    bank.getBankId(),
                    bank.getBankAccountId(),
                    Boolean.TRUE
            );
            mappings.add(mapping);
        }
        userBankMappingRepository.saveAll(mappings);
    }
}
