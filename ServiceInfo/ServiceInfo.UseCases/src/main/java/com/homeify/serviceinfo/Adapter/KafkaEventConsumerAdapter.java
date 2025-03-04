package com.homeify.serviceinfo.Adapter;

import java.util.function.Consumer;

public interface KafkaEventConsumerAdapter {
    void registerListener(String topic, String groupId, Consumer<Object> listener);
}
