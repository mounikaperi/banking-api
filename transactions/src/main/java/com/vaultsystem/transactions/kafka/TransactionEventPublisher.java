package com.vaultsystem.transactions.kafka;

import com.vaultsystem.transactions.adapters.TransactionAdapter;
import com.vaultsystem.transactions.dto.TransactionDTO;
import com.vaultsystem.transactions.entities.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/*
* This Kafka event publisher will be triggered whenever a new transaction is saved in any of the three banks' repositories.
*/
@Component
@RequiredArgsConstructor
public class TransactionEventPublisher {
    private final KafkaTemplate<String, TransactionDTO> kafkaTemplate;
    private final TransactionAdapter transactionAdapter;

    @Value("${spring.kafka.topic.transaction-events}")
    private String transactionEventsTopic;

    // Publish Events to Kafka
    public void publishEvent(TransactionDTO transactionDTO) {
        kafkaTemplate.send(transactionEventsTopic, transactionDTO);
    }

    // Handle database event insertions
    public void publishTransaction(Transaction transaction) {
        TransactionDTO transactionDTO = transactionAdapter.fromAxis(transaction);
        publishEvent(transactionDTO);
    }
}
