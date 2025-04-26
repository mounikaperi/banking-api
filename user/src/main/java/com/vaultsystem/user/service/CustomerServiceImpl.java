package com.vaultsystem.user.service;

import com.vaultsystem.user.dto.CustomerDTO;
import com.vaultsystem.user.exceptions.CustomerAlreadyExistsException;
import com.vaultsystem.user.model.Customer;
import com.vaultsystem.user.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final UserBankMappingService userBankMappingService;


    @Transactional
    public void registerCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.getCustomerId(), customerDTO.getUserId(), customerDTO.getPassword(), customerDTO.getEmailAddress(),
                customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getAddress(),
                customerDTO.getCity(), customerDTO.getPostalCode(), customerDTO.getDateOfBirth());
        Customer savedUser = customerRepository.save(customer);
        userBankMappingService.registerBankIdAccountIdOfUser(customerDTO.getCustomerId(), customerDTO.getBanksDTOList());
        if (savedUser == null) {
            throw new CustomerAlreadyExistsException("User is already registered with given email" + customer.getEmailAddress());
        }
    }
}
