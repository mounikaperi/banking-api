package com.bankingapi.accounts.repository;

import com.bankingapi.accounts.entities.BankAccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HdfcAccountRepository extends JpaRepository<BankAccountDetails, String> {
    List<BankAccountDetails> findByAccountId(String accountId);
}

