package com.vaultsystem.customer.controller;

import com.vaultsystem.customer.constants.CustomerConstants;
import com.vaultsystem.customer.dto.BankDTO;
import com.vaultsystem.customer.dto.CustomerBankDetailsDTO;
import com.vaultsystem.customer.dto.CustomerDTO;
import com.vaultsystem.customer.dto.ResponseDTO;
import com.vaultsystem.customer.service.CustomerService;
import com.vaultsystem.customer.service.UserBankMappingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final UserBankMappingService userBankMappingService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerCustomer(@Valid @RequestBody CustomerDTO customer) {
        customerService.registerCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(CustomerConstants.STATUS_201, CustomerConstants.MESSAGE_201));
    }

    @PostMapping("/link-account")
    public ResponseEntity<ResponseDTO> linkNewAccount(@Valid @RequestBody List<BankDTO> bankDTO) {
        userBankMappingService.registerBankIdAccountIdOfUser(bankDTO.getFirst().getCustomerId(), bankDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(CustomerConstants.STATUS_201, CustomerConstants.BANK_LINKED_SUCCESSFULLY));
    }

    @PostMapping("/fetch-customer-details")
    public ResponseEntity<List<CustomerBankDetailsDTO>> fetchCustomerDetails(@RequestParam String userId) {
        List<CustomerBankDetailsDTO> result = customerService.fetchCustomerDetails(userId);
        return ResponseEntity.ok(result);
    }
}
