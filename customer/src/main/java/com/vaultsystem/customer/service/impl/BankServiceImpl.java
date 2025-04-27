package com.vaultsystem.customer.service.impl;

import com.vaultsystem.customer.dto.BankDTO;
import com.vaultsystem.customer.dto.UserBankMappingDTO;
import com.vaultsystem.customer.entities.Bank;
import com.vaultsystem.customer.entities.UserBankMapping;
import com.vaultsystem.customer.repository.BankRepository;
import com.vaultsystem.customer.repository.UserBankMappingRepository;
import com.vaultsystem.customer.service.BankService;
import com.vaultsystem.customer.service.UserBankMappingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;
    @Override
    public List<BankDTO> fetchAllBanks() {
        List<Bank> fetchedBanks = bankRepository.findAll();
        List<BankDTO> bankDTOS = fetchedBanks.stream().map(bank -> {
            BankDTO dto = new BankDTO();
            dto.setBankId(bank.getBankId());
            dto.setBankName(bank.getBankName());
            dto.setBankCode(bank.getBankCode());
            return dto;
        }).collect(Collectors.toList());
        return bankDTOS;
    }
}
