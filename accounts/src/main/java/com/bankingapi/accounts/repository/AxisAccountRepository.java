package com.bankingapi.accounts.repository;

import com.bankingapi.accounts.entities.BankAccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AxisAccountRepository extends JpaRepository<BankAccountDetails, String> {
    List<Acc> findByAccountNumber(String accountNumber);
}

