package com.bankingapi.accounts.mappers;

import com.bankingapi.accounts.dto.BankAccountDetailsDTO;
import com.bankingapi.accounts.repository.AxisAccountRepository;
import com.bankingapi.accounts.repository.HdfcAccountRepository;
import com.bankingapi.accounts.repository.IciciAccountRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BankRepositoryMapper {
    private final AxisAccountRepository axisRepo;
    private final IciciAccountRepository iciciRepo;
    private final HdfcAccountRepository hdfcRepo;
    private final AccountAdapter accountAdapter;

    private Map<String, Function<String, List<BankAccountDetailsDTO>>> repoFetchers;

    @PostConstruct
    public void init() {
        repoFetchers = new HashMap<>();
        repoFetchers.put("AXIS", (bankAccountId) -> axisRepo.findByAccountId(bankAccountId).stream().map(accountAdapter::fromAxis).collect(Collectors.toList()));
        repoFetchers.put("ICICI", (bankAccountId) -> iciciRepo.findByAccountId(bankAccountId).stream().map(accountAdapter::fromIcici).collect(Collectors.toList()));
        repoFetchers.put("HDFC", (bankAccountId) -> hdfcRepo.findByAccountId(bankAccountId).stream().map(accountAdapter::fromHdfc).collect(Collectors.toList()));
    }

    public List<BankAccountDetailsDTO> fetchAccountDetails(String bankName, String accountId) {
        Function<String, List<BankAccountDetailsDTO>> fetcher = repoFetchers.get(bankName.toUpperCase());
        if (fetcher != null) {
            return fetcher.apply(accountId);
        }
        return Collections.emptyList();
    }
}
