package com.vaultsystem.customer.controller;

import com.vaultsystem.customer.constants.CustomerConstants;
import com.vaultsystem.customer.dto.*;
import com.vaultsystem.customer.service.BankService;
import com.vaultsystem.customer.service.CustomerService;
import com.vaultsystem.customer.service.UserBankMappingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final UserBankMappingService userBankMappingService;
    private final BankService bankService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerCustomer(@Valid @RequestBody CustomerDTO customer) {
        customerService.registerCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(CustomerConstants.STATUS_201, CustomerConstants.MESSAGE_201));
    }

    @PostMapping("/linkNewBankAccount")
    public ResponseEntity<ResponseDTO> linkNewBankAccount(@Valid @RequestBody List<UserBankMappingDTO> bankDTO) {
        userBankMappingService.registerBankIdAccountIdOfUser(bankDTO.getFirst().getCustomerId(), bankDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(CustomerConstants.STATUS_201, CustomerConstants.BANK_LINKED_SUCCESSFULLY));
    }

    @GetMapping("/fetchAllUserBankAccounts")
    public ResponseEntity<List<CustomerBankDetailsDTO>> fetchAllUserBankAccounts(@RequestParam String userId) {
        List<CustomerBankDetailsDTO> result = customerService.fetchCustomerDetails(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/fetchAllAvailableBanks")
    public ResponseEntity<List<BankDTO>> fetchAllAvailableBanks() {
        List<BankDTO> fetchedAvailableBanks = bankService.fetchAllBanks();
        return ResponseEntity.ok(fetchedAvailableBanks);
    }
}
