package com.bankingapi.accounts.service.implementation;

import com.bankingapi.accounts.dto.BankAccountDetailsDTO;
import com.bankingapi.accounts.mappers.BankRepositoryMapper;
import com.bankingapi.accounts.repository.AxisAccountRepository;
import com.bankingapi.accounts.repository.HdfcAccountRepository;
import com.bankingapi.accounts.repository.IciciAccountRepository;
import com.bankingapi.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final BankRepositoryMapper bankRepositoryMapper;

    @Override
    public List<BankAccountDetailsDTO> getAllUserBankAccountDetails(String userId) {
        // feign client call and take the banks that are applicable for user
        List<BankAccountDetailsDTO> allResponses = new ArrayList<>();
        for (String bank : banks) {
            List<BankAccountDetailsDTO> allBankAccountDetailsResponse = bankRepositoryMapper.fetchAccountDetails(bank, accountId);
            allResponses.addAll(allBankAccountDetailsResponse);
        }
        return allResponses;
    }
}
