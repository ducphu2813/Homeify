package com.homeify.serviceinfo.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Transportation {

    private String id;
    private String name;
    private String plateNumber;
    private Integer capacity;

    private List<Seat> seats;
}
