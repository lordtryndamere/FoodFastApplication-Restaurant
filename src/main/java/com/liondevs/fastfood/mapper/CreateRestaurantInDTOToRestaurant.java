package com.liondevs.fastfood.mapper;

import com.liondevs.fastfood.controller.dtos.CreateRestaurantDTO;
import com.liondevs.fastfood.persistence.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CreateRestaurantInDTOToRestaurant implements  IMapper<CreateRestaurantDTO, Restaurant> {

    @Override
    public Restaurant map(CreateRestaurantDTO in) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(in.getName());
        restaurant.setDescription(in.getDescription());
        restaurant.setPhoto(in.getPhoto());
        restaurant.setLatitude(in.getLatitude());
        restaurant.setLongitude(in.getLongitude());
        restaurant.setCreatedAt(Instant.now());
        restaurant.setUpdatedAt(Instant.now());
        return restaurant;
    }
}
