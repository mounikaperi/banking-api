package com.bankingapi.accounts.controller;

import com.bankingapi.accounts.dto.BankAccountDetailsDTO;
import com.bankingapi.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CRUD REST APIs for Accounts in EazyBank")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    private final IAccountsService iAccountsService;

    public AccountsController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }

    @GetMapping("/getAllUserBankAccountDetails")
    public ResponseEntity<List<BankAccountDetailsDTO>> getAllUserBankAccountDetails(@RequestParam String userId) {
        return null;
    }
}
