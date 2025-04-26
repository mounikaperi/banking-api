package com.vaultsystem.user.repository;

import com.vaultsystem.user.model.Customer;
import com.vaultsystem.user.model.UserBankMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserBankMappingRepository extends JpaRepository<UserBankMapping, Long> {

}
