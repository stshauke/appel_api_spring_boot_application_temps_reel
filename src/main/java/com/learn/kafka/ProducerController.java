package com.learn.kafka;

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

    @GetMapping("/exchange-rate")
    public ResponseEntity<?> fetchAndSendExchangeRate(@RequestParam String base) {
        var exchangeData = exchangeRateService.getExchangeRates(base);
        messageProducer.sendMessage("mon-tunnel-topic", exchangeData.toString());
        return ResponseEntity.ok(exchangeData);
    }
}
