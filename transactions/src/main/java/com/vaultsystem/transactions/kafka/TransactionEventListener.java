package com.vaultsystem.transactions.kafka;

import com.vaultsystem.transactions.dto.TransactionDTO;
import com.vaultsystem.transactions.service.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableKafka
public class TransactionEventListener {
    private final ElasticSearchService elasticSearchService;
    @KafkaListener(topics = "${spring.kafka.topic.transaction-events}", groupId = "txn-consumer")
    public void consume(TransactionDTO txnDoc) {
        elasticSearchService.indexTransaction(txnDoc);
    }
}
