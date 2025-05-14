package com.homeify.serviceinfo.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
@Entity
public class CityModel {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "city_code")
    private String cityCode;

    //liên kết đến bảng pickuparea
     @OneToMany(mappedBy = "city"
             , cascade = CascadeType.REMOVE)
     private List<PickupAreaModel> pickupAreas;
}
