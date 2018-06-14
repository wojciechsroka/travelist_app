package com.mkyong.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class CityDTO {
    private Long id;

    private String name;

    private String description;


}
