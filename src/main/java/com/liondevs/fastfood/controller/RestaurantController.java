package com.liondevs.fastfood.controller;

import com.liondevs.fastfood.controller.dtos.CreateRestaurantDTO;

import com.liondevs.fastfood.controller.dtos.UpdateRestaurantDTO;
import com.liondevs.fastfood.controller.dtos.queries.FindAllRestaurantsByUserCoordinatesRequest;
import com.liondevs.fastfood.controller.dtos.queries.FindAllRestaurantsByUserCoordinatesResponse;
import com.liondevs.fastfood.persistence.entity.Restaurant;
import com.liondevs.fastfood.service.RestaurantService;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.context.config.annotation.RefreshScope;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RestController
@RefreshScope // nos permite actualizar la config sin reiniciar, se debe usar en donde se necesite cambie una propiedad o valor
@RequestMapping("/restaurant")
@Slf4j
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService ) {

        this.restaurantService = restaurantService;
    }


    @PostMapping("/")
    public ResponseEntity<Map<String, Restaurant>> save(@Valid @RequestBody CreateRestaurantDTO restaurant) {
        return restaurantService.save(restaurant);
    }
    @GetMapping("/{id}")
    ResponseEntity<Map<String,Restaurant>> one(@PathVariable Integer id) {
        return restaurantService.one(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<Map<String,String>> update(@Valid @RequestBody UpdateRestaurantDTO restaurant, @PathVariable Integer id) {
        return restaurantService.update(restaurant,id);
    }

    @GetMapping("/")
    ResponseEntity<?> all(){
        return restaurantService.all();
    }

    @PostMapping("/find-by-coordinates")
    List<FindAllRestaurantsByUserCoordinatesResponse> findAllRestaurantsByUserCoordinates(
            @Valid @RequestBody FindAllRestaurantsByUserCoordinatesRequest request){
        return restaurantService.findAllRestaurantsByUserCoordinates(request);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete (@Valid @PathVariable Integer id){
        return  restaurantService.delete(id);
    }


    @PatchMapping("/update-latitude/{id}")
    ResponseEntity<?> updateLatitude (@Valid @PathVariable Integer id, @RequestBody String latitude){
        return  restaurantService.updateLatitude(latitude,id);
    }

}
