package com.learn.kafka.repository;

import com.learn.kafka.model.ExchangeRateDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends ElasticsearchRepository<ExchangeRateDocument, String> {
}
