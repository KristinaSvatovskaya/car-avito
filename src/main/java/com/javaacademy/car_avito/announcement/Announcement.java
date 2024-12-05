package com.javaacademy.car_avito.announcement;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
//@EqualsAndHashCode(of = "id")
//@Builder
public class Announcement {
    private Integer id;
    private String brandName;
    private String colour;
    private BigDecimal price;
}
