package com.learn.kafka.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.Map;

@Data
@Document(indexName = "exchange-rates")
public class ExchangeRateDocument {

    @Id
    private String id;

    private String base;
    private String date; // de l'API, format texte
    private Map<String, Double> rates;

    private Date timestamp; // ⏱️ automatiquement ajouté à l'enregistrement
}
