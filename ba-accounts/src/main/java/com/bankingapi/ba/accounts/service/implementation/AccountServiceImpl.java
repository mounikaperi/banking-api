package com.bankingapi.ba.accounts.service.implementation;

import com.bankingapi.ba.accounts.dto.CustomerDTO;
import com.bankingapi.ba.accounts.entities.Customer;
import com.bankingapi.ba.accounts.mapper.CustomerMapper;
import com.bankingapi.ba.accounts.repository.AccountsRepository;
import com.bankingapi.ba.accounts.repository.CustomerRepository;
import com.bankingapi.ba.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer is already registered with given mobileNumber" + customerDTO.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }
}
