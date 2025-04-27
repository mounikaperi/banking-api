package com.bankingapi.accounts.service.implementation;

import com.bankingapi.accounts.constants.AccountsConstants;
import com.bankingapi.accounts.dto.BankAccountDetailsDTO;
import com.bankingapi.accounts.entities.Accounts;
import com.bankingapi.accounts.entities.Customer;
import com.bankingapi.accounts.exception.ResourceNotFoundException;
import com.bankingapi.accounts.mapper.AccountsMapper;
import com.bankingapi.accounts.mapper.CustomerMapper;
import com.bankingapi.accounts.repository.AccountsRepository;
import com.bankingapi.accounts.repository.CustomerRepository;
import com.bankingapi.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AxisAccountsRepository axisAccountsRepository;
    private final IciciAccountsRepository iciciAccountsRepository;
    private final HdfcAccountsRepository hdfcAccountsRepository;

    public AccountServiceImpl(AxisAccountsRepository axisAccountsRepository, IciciAccountsRepository iciciAccountsRepository, HdfcAccountsRepository hdfcAccountsRepository) {
        this.axisAccountsRepository = axisAccountsRepository;
        this.iciciAccountsRepository = iciciAccountsRepository;
        this.hdfcAccountsRepository = hdfcAccountsRepository;
    }

    @Override
    public List<BankAccountDetailsDTO> getAllUserBankAccountDetails(String userId) {
        // feign client call and take the banks that are applicable for user

        return null;
    }
}
