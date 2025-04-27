package com.vaultsystem.customer.service;

import com.vaultsystem.customer.dto.BankDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BankService {
    List<BankDTO> fetchAllBanks();
}
