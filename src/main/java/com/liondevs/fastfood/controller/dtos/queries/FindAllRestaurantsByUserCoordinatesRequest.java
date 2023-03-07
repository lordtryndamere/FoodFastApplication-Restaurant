package com.liondevs.fastfood.controller.dtos.queries;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FindAllRestaurantsByUserCoordinatesRequest {
    @NotEmpty(message = "user latitude is required")
    @NotBlank(message = "latitude user cannot be empty")
    private String latitude;

    @NotEmpty(message = "user longitude is required")
    @NotBlank(message = "longitude user cannot be empty")
    private String longitude;

}
