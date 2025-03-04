package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Adapter.KafkaEventConsumerAdapter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

//@Service
public class KafkaEventConsumerAdapterImpl /*implements KafkaEventConsumerAdapter */ {

//    private final Map<String, Consumer<Object>> listeners = new HashMap<>();
//
//    @Override
//    public void registerListener(String topic, String groupId, Consumer<Object> listener) {
//        String key = topic + "-" + groupId;
//        listeners.put(key, listener);
//    }
//
//    @KafkaListener(topics = "test-topic", groupId = "info-auth-group")
//    public void listenTestTopic(Object message,
//                                @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
//                                @Header(KafkaHeaders.GROUP_ID) String groupId) {
//        System.out.println("Received message in Info Service:");
//        System.out.println("  Topic: " + topic);
//        System.out.println("  GroupId: " + groupId);
//        System.out.println("  Message: " + message);
//
//        String key = "test-topic-info-auth-group";
//        Consumer<Object> listener = listeners.get(key);
//        if (listener != null) {
//            listener.accept(message);
//        }
//    }

}
