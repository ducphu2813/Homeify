package com.homeify.serviceinfo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transportations")
@Entity
public class TransportationModel {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "capacity")
    private Integer capacity;

    //liêm kết của SeatModel
    @OneToMany(mappedBy = "transportation"
                , cascade = CascadeType.REMOVE)
    private List<SeatModel> seats;
}
