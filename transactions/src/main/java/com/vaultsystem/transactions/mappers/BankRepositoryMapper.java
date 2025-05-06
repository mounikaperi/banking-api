package com.vaultsystem.transactions.mappers;

import com.vaultsystem.transactions.adapters.TransactionAdapter;
import com.vaultsystem.transactions.dto.TransactionDTO;
import com.vaultsystem.transactions.repository.AxisTransactionRepository;
import com.vaultsystem.transactions.repository.HdfcTransactionRepository;
import com.vaultsystem.transactions.repository.IciciTransactionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BankRepositoryMapper {
    private final AxisTransactionRepository axisTransactionRepository;
    private final IciciTransactionRepository iciciTransactionRepository;
    private final HdfcTransactionRepository hdfcTransactionRepository;
    private final TransactionAdapter transactionAdapter;

    private Map<String, BiFunction<String, Pageable, List<TransactionDTO>>> repoFetchers;

    @PostConstruct
    public void init() {
        repoFetchers = new HashMap<>();
        repoFetchers.put("AXIS", (accountId, pageable) -> axisTransactionRepository.findByAccountId(accountId, pageable).stream().map(transactionAdapter::fromAxis).collect(Collectors.toList()));
        repoFetchers.put("ICICI", (accountId, pageable) -> iciciTransactionRepository.findByAccountId(accountId, pageable).stream().map(transactionAdapter::fromIcici).collect(Collectors.toList()));
        repoFetchers.put("HDFC", (accountId, pageable) -> hdfcTransactionRepository.findByAccountId(accountId, pageable).stream().map(transactionAdapter::fromHdfc).collect(Collectors.toList()));
    }

    public List<TransactionDTO> fetchTransactions(String bankName, String accountId, Pageable pageable) {
        BiFunction<String, Pageable, List<TransactionDTO>> fetcher = repoFetchers.get(bankName.toUpperCase());
        if (fetcher != null) {
            return fetcher.apply(accountId, pageable);
        }
        return Collections.emptyList();
    }
}
