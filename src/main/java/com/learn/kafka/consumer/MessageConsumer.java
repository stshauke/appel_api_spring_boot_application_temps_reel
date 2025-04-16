package com.learn.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.kafka.model.ExchangeRateDocument;
import com.learn.kafka.repository.ExchangeRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class MessageConsumer {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @KafkaListener(topics = "mon-tunnel-topic", groupId = "message-group-1")
    public void listen(String message) {
        log.info("📥 Message received in consumer: {}", message);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.readValue(message, Map.class);

            ExchangeRateDocument doc = new ExchangeRateDocument();
            doc.setId(UUID.randomUUID().toString());
            doc.setBase((String) map.get("base"));
            doc.setDate((String) map.get("date"));
            doc.setRates((Map<String, Double>) map.get("rates"));
            doc.setTimestamp(new Date()); // ✅ Ajout explicite du champ timestamp

            exchangeRateRepository.save(doc);
            log.info("📤 Document saved to Elasticsearch");

        } catch (Exception e) {
            log.error("❌ Failed to parse or index message", e);
        }
    }
}
