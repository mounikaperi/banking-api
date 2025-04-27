package com.vaultsystem.customer.repository;

import com.vaultsystem.customer.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

    @Query(value = "SELECT b.bankId, b.bankName, b.bankCode, ubm.bankAccountId " +
            "FROM user_bank_mapping ubm " +
            "JOIN banks b ON ubm.bankId = b.bankId " +
            "WHERE ubm.userId = :userId", nativeQuery = true)
    List<Object[]> fetchCustomerBankDetails(@Param("userId") String userId);
}
