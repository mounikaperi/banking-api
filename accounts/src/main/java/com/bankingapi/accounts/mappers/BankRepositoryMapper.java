package com.bankingapi.accounts.mappers;

import com.bankingapi.accounts.dto.BankAccountDetailsDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BankRepositoryMapper {
    private final AxisRepository axisRepo;
    private final IciciRepository iciciRepo;
    private final HdfcRepository hdfcRepo;
    private final AccountAdapter accountAdapter;

    private Map<String, Function<String, List<BankAccountDetailsDTO>>> repoFetchers;

    @PostConstruct
    public void init() {
        repoFetchers = new HashMap<>();
        repoFetchers.put("AXIS", (bankAccountId) -> axisRepo.findByAccountId(bankAccountId).stream().map(accountAdapter::fromAxis).collect(Collectors.toList()));
        repoFetchers.put("ICICI", (bankAccountId) -> iciciRepo.findByAccountId(bankAccountId).stream().map(accountAdapter::fromIcici).collect(Collectors.toList()));
        repoFetchers.put("HDFC", (bankAccountId) -> hdfcRepo.findByAccountId(bankAccountId).stream().map(accountAdapter::fromHdfc).collect(Collectors.toList()));
    }

}
