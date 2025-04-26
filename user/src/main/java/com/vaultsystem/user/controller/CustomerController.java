package com.vaultsystem.user.controller;

import com.vaultsystem.user.constants.CustomerConstants;
import com.vaultsystem.user.dto.CustomerDTO;
import com.vaultsystem.user.dto.ResponseDTO;
import com.vaultsystem.user.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerCustomer(@Valid @RequestBody CustomerDTO customer) {
        customerService.registerCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(CustomerConstants.STATUS_201, CustomerConstants.MESSAGE_201));
    }
}
