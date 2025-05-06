package com.vaultsystem.transactions.repository;

import com.vaultsystem.transactions.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HdfcTransactionRepository extends JpaRepository<Transaction, String> {
    Page<Transaction> findByAccountId(String accountId, Pageable pageable);
}
