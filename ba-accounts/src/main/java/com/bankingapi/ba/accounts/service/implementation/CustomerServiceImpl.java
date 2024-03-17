package com.bankingapi.ba.accounts.service.implementation;

import com.bankingapi.ba.accounts.dto.AccountsDTO;
import com.bankingapi.ba.accounts.dto.CardsDTO;
import com.bankingapi.ba.accounts.dto.CustomerDetailsDTO;
import com.bankingapi.ba.accounts.dto.LoansDTO;
import com.bankingapi.ba.accounts.entities.Accounts;
import com.bankingapi.ba.accounts.entities.Customer;
import com.bankingapi.ba.accounts.exception.ResourceNotFoundException;
import com.bankingapi.ba.accounts.mapper.AccountsMapper;
import com.bankingapi.ba.accounts.mapper.CustomerMapper;
import com.bankingapi.ba.accounts.repository.AccountsRepository;
import com.bankingapi.ba.accounts.repository.CustomerRepository;
import com.bankingapi.ba.accounts.service.ICustomersService;
import com.bankingapi.ba.accounts.service.client.CardsFeignClient;
import com.bankingapi.ba.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        ResponseEntity<LoansDTO> loansDTOResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDTO.setLoansDTO(loansDTOResponseEntity.getBody());
        ResponseEntity<CardsDTO> cardsDTOResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        customerDetailsDTO.setCardsDTO(cardsDTOResponseEntity.getBody());
        return customerDetailsDTO;
    }
}
