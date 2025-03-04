package com.homeify.serviceinfo.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cities")
public class CityModel {

    @Id
    private String id;
    private String name;
    private String cityCode;
}
