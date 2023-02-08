package com.liondevs.fastfood.mapper;

import com.liondevs.fastfood.controller.dtos.CreateRestaurantDTO;
import com.liondevs.fastfood.controller.dtos.UpdateRestaurantDTO;
import com.liondevs.fastfood.persistence.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.time.Instant;
@Component
public class UpdateRestaurantInDTOtoRestaurant implements IMapper<UpdateRestaurantDTO, Restaurant> {
    @Override
    public Restaurant map(UpdateRestaurantDTO in) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(in.getName());
        restaurant.setDescription(in.getDescription());
        restaurant.setPhoto(in.getPhoto());
        restaurant.setLatitude(in.getLatitude());
        restaurant.setLongitude(in.getLongitude());
        restaurant.setUpdatedAt(Instant.now());
        return restaurant;
    }
}
