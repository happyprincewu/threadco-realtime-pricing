package com.threadco.pricing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

/** Publishes price decisions to the price.updates topic consumed by stores and the website. */
public class PriceStreamPublisher {
    private final KafkaProducer<String, String> producer;
    private final ObjectMapper json = new ObjectMapper();

    public PriceStreamPublisher(String bootstrap) {
        Properties p = new Properties();
        p.put("bootstrap.servers", bootstrap);
        p.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        p.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<>(p);
    }

    public void publish(PriceDecision d) throws Exception {
        producer.send(new ProducerRecord<>("price.updates", d.sku + "@" + d.store, json.writeValueAsString(d)));
    }
}
