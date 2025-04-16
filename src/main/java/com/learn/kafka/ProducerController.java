package com.learn.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.kafka.service.ExchangeRateService;
import com.learn.kafka.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProducerController {

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private ObjectMapper objectMapper; // 👈 Pour convertir Map en JSON

    @GetMapping("/exchange-rate")
    public ResponseEntity<?> fetchAndSendExchangeRate(@RequestParam String base) {
        try {
            var exchangeData = exchangeRateService.getExchangeRates(base);

            // ✅ Conversion propre de Map -> JSON
            String jsonMessage = objectMapper.writeValueAsString(exchangeData);

            // ✅ Envoi du JSON dans Kafka
            messageProducer.sendMessage("mon-tunnel-topic", jsonMessage);

            return ResponseEntity.ok(exchangeData);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ Erreur de sérialisation ou d'envoi Kafka");
        }
    }
}
