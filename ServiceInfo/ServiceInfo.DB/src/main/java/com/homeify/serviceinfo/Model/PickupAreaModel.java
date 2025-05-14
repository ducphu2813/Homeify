package com.homeify.serviceinfo.Model;

import com.homeify.serviceinfo.Entities.City;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pickup_areas")
@Entity
public class PickupAreaModel {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityModel city;

    @Column(name = "number")
    private String number;
}
