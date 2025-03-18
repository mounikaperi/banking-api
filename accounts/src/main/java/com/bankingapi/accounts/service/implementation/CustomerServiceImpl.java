package com.bankingapi.accounts.service.implementation;

import com.bankingapi.accounts.dto.AccountsDTO;
import com.bankingapi.accounts.dto.CardsDTO;
import com.bankingapi.accounts.dto.CustomerDetailsDTO;
import com.bankingapi.accounts.dto.LoansDTO;
import com.bankingapi.accounts.entities.Accounts;
import com.bankingapi.accounts.entities.Customer;
import com.bankingapi.accounts.exception.ResourceNotFoundException;
import com.bankingapi.accounts.mapper.AccountsMapper;
import com.bankingapi.accounts.mapper.CustomerMapper;
import com.bankingapi.accounts.repository.AccountsRepository;
import com.bankingapi.accounts.repository.CustomerRepository;
import com.bankingapi.accounts.service.ICustomersService;
import com.bankingapi.accounts.service.client.CardsFeignClient;
import com.bankingapi.accounts.service.client.LoansFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDTO customerDetailsDTO = CustomerMapper.mapToCustomerDetailsDTO(customer, new CustomerDetailsDTO());
        customerDetailsDTO.setAccountsDTO(AccountsMapper.mapToAccountsDTO(accounts, new AccountsDTO()));
        ResponseEntity<List<LoansDTO>> loansDTOResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,mobileNumber);
        if (loansDTOResponseEntity != null) {
            customerDetailsDTO.setLoansDTO(loansDTOResponseEntity.getBody());
        }
        ResponseEntity<List<CardsDTO>> cardsDTOResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        if (cardsDTOResponseEntity != null) {
            customerDetailsDTO.setCardsDTO(cardsDTOResponseEntity.getBody());
        }
        return customerDetailsDTO;
    }
}
