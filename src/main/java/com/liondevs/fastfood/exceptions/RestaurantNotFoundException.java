package com.liondevs.fastfood.exceptions;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(Integer id){
        super("Could not find restaurant with id :"+id);
    }
}
