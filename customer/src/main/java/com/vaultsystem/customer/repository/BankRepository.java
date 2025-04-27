package com.vaultsystem.customer.repository;

import com.vaultsystem.customer.entities.Bank;
import com.vaultsystem.customer.entities.UserBankMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, String> {

}
