package com.homeify.auth.Adapter;

public interface KafkaProducerAdapter {
    void sendMessage(String topic, Object message);
}
