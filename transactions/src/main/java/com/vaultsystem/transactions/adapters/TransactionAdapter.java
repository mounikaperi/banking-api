package com.vaultsystem.transactions.adapters;

import com.vaultsystem.transactions.dto.TransactionDTO;
import com.vaultsystem.transactions.entities.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*
    TransactionAdapter takes all the different schemas of different banks and
    returns a unified schema based response to the controller
 */
@Component
public class TransactionAdapter {

    public TransactionDTO fromAxis(Transaction txn) {
        return new TransactionDTO(txn.getTransactionId(), txn.getAccountId(),
        txn.getTransactionDate(), txn.getTransactionCurrencyCode(), txn.getTransactionType(),
        txn.getTransactionParticulars(), txn.getTransactionAmount(), txn.getChannel(),
        txn.getTransactionStatus(), "AXIS");
    }

    public TransactionDTO fromIcici(Transaction txn) {
        return new TransactionDTO(txn.getTransactionId(), txn.getAccountId(),
                txn.getTransactionDate(), txn.getTransactionCurrencyCode(), txn.getTransactionType(),
                txn.getTransactionParticulars(), txn.getTransactionAmount(), txn.getChannel(),
                txn.getTransactionStatus(), "ICICI");
    }

    public TransactionDTO fromHdfc(Transaction txn) {
        return new TransactionDTO(txn.getTransactionId(), txn.getAccountId(),
                txn.getTransactionDate(), txn.getTransactionCurrencyCode(), txn.getTransactionType(),
                txn.getTransactionParticulars(), txn.getTransactionAmount(), txn.getChannel(),
                txn.getTransactionStatus(), "HDFC");
    }
}

