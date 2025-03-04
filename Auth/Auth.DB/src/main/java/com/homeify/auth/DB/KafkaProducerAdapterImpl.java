package com.homeify.auth.DB;

import com.homeify.auth.Adapter.KafkaProducerAdapter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerAdapterImpl implements KafkaProducerAdapter {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerAdapterImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topic, Object message) {
        try {
            kafkaTemplate.send(topic, message);
            System.out.println("Sent message to topic " + topic + ": " + message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send Kafka message", e);
        }
    }
}
