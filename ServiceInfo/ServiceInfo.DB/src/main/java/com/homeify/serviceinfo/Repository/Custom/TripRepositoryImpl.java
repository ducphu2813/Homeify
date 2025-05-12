package com.homeify.serviceinfo.Repository.Custom;

import com.homeify.serviceinfo.Model.TripModel;
import com.homeify.serviceinfo.Repository.Custom.Interface.TripRepositoryCustom;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class TripRepositoryImpl implements TripRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public TripRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void updateAvailableSeats(String tripId, int seatCount, String action) {
        Query query = new Query(Criteria.where("_id").is(tripId));

        Update update = new Update();

        if ("book".equalsIgnoreCase(action)) {
            update.inc("availableSeats", -seatCount); // Giảm số ghế
        } else if ("delete".equalsIgnoreCase(action)) {
            update.inc("availableSeats", seatCount); // Tăng số ghế
        } else {
            throw new IllegalArgumentException("Invalid action: " + action);
        }

        mongoTemplate.updateFirst(query, update, TripModel.class);
    }
}