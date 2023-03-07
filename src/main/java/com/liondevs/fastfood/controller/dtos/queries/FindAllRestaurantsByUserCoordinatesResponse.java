package com.liondevs.fastfood.controller.dtos.queries;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FindAllRestaurantsByUserCoordinatesResponse {


    private String name;

    private String description;

    private String photo;

    private double distance;
}
