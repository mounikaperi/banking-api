package com.vaultsystem.customer.service;

import com.vaultsystem.customer.dto.CustomerBankDetailsDTO;
import com.vaultsystem.customer.dto.CustomerDTO;
import com.vaultsystem.customer.exceptions.CustomerAlreadyExistsException;
import com.vaultsystem.customer.model.Customer;
import com.vaultsystem.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final UserBankMappingService userBankMappingService;

    @Transactional
    public void registerCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.getUserId(), customerDTO.getPassword(), customerDTO.getEmailAddress(),
                customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getAddress(),
                customerDTO.getCity(), customerDTO.getPostalCode(), customerDTO.getDateOfBirth());
        Customer savedUser = customerRepository.save(customer);
        userBankMappingService.registerBankIdAccountIdOfUser(customerDTO.getUserId(), customerDTO.getBanksDTOList());
        if (savedUser == null) {
            throw new CustomerAlreadyExistsException("User is already registered with given email" + customer.getEmailAddress());
        }
    }

    @Override
    public List<CustomerBankDetailsDTO> fetchCustomerDetails(String userId) {
        List<Object[]> results = customerRepository.fetchCustomerBankDetails(userId);
        List<CustomerBankDetailsDTO> customerBanks = new ArrayList<>();
        for (Object[] row: results) {
            CustomerBankDetailsDTO dto = new CustomerBankDetailsDTO(
                    (String) row[0],  // bankId
                    (String) row[1],  // bankName
                    (String) row[2],  // bankCode
                    (String) row[3]   // bankAccountId
            );
            customerBanks.add(dto);
        }
        return customerBanks;
    }
}
