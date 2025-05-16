package com.homeify.serviceinfo.AdapterImpl;

import com.homeify.serviceinfo.Adapter.KafkaEventConsumerAdapter;
import com.homeify.serviceinfo.Repository.TripRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class KafkaEventConsumerAdapterImpl implements KafkaEventConsumerAdapter {

    private final Map<String, Consumer<Object>> listeners = new HashMap<>();

    private final TripRepository tripRepository;

    public KafkaEventConsumerAdapterImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public void registerListener(String topic, String groupId, Consumer<Object> listener) {
        String key = topic + "-" + groupId;
        listeners.put(key, listener);
    }

    @KafkaListener(topics = "booking-delete-topic", groupId = "booking-group")
    public void consume(String tripId, int seatCount)
    {
        //đọc thử 2 dữ lieu từ topic
        System.out.println("Received message from topic booking-expire-topic: " + tripId + ", " + seatCount);

        //tìm trip theo tripId

    }

}
