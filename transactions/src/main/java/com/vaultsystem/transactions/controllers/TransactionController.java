package com.vaultsystem.transactions.controllers;

import com.vaultsystem.transactions.dto.TransactionDTO;
import com.vaultsystem.transactions.entities.Transaction;
import com.vaultsystem.transactions.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/getAllAccountTransactions")
    public ResponseEntity<List<TransactionDTO>> getAllBankAccountTransactions(
            @RequestParam String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "transactionDate") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection
    ) {
        List<TransactionDTO> fetchedTransactions = transactionService.getAllTransactions(userId, page, size, sortBy, sortDirection);
        return ResponseEntity.status(HttpStatus.OK).body(fetchedTransactions);
    }
}
