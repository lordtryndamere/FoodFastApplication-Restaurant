package com.liondevs.fastfood.service;


import com.liondevs.fastfood.controller.dtos.CreateRestaurantDTO;
import com.liondevs.fastfood.controller.dtos.UpdateRestaurantDTO;
import com.liondevs.fastfood.controller.dtos.queries.FindAllRestaurantsByUserCoordinatesRequest;
import com.liondevs.fastfood.controller.dtos.queries.FindAllRestaurantsByUserCoordinatesResponse;
import com.liondevs.fastfood.exceptions.DefaultException;
import com.liondevs.fastfood.exceptions.RestaurantNotFoundException;
import com.liondevs.fastfood.mapper.CreateRestaurantInDTOToRestaurant;
import com.liondevs.fastfood.mapper.UpdateRestaurantInDTOtoRestaurant;
import com.liondevs.fastfood.persistence.entity.Restaurant;
import com.liondevs.fastfood.persistence.repository.RestaurantRepository;

import com.liondevs.fastfood.utils.Utils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.*;


@Service
public class RestaurantService {
     //inyeccion por anotacion @AutoWired
  private final  RestaurantRepository restaurantRepository;
  private final CreateRestaurantInDTOToRestaurant mapper;
  private final UpdateRestaurantInDTOtoRestaurant updateMapper;



   public  RestaurantService(RestaurantRepository restaurantRepository,
                             CreateRestaurantInDTOToRestaurant mapper,
                             UpdateRestaurantInDTOtoRestaurant updateMapper
                          ){

       this.restaurantRepository = restaurantRepository;
       this.mapper = mapper;
       this.updateMapper = updateMapper;




   }


   private String distance(String... coordinates){
       List<String> listOfCoordinates = new ArrayList<>(Arrays.asList(coordinates));
    return  Utils
            .getDistanceInKilometers(
                    Double.parseDouble(listOfCoordinates.get(0)),
                    Double.parseDouble(listOfCoordinates.get(1)),
                    Double.parseDouble(listOfCoordinates.get(2)),
                    Double.parseDouble(listOfCoordinates.get(3)),
                    "K"
            );
   }
    public List<FindAllRestaurantsByUserCoordinatesResponse> findAllRestaurantsByUserCoordinates(FindAllRestaurantsByUserCoordinatesRequest coordinates){
        List<Restaurant> restaurants = (List<Restaurant>) restaurantRepository.findAll();
        List<FindAllRestaurantsByUserCoordinatesResponse> response = new ArrayList<>(restaurants.stream().map((restaurant -> {
            String distance =
                    this.distance(coordinates.getLongitude(),coordinates.getLatitude(),restaurant.getLongitude(),restaurant.getLatitude());
            Type objectType = new TypeToken<FindAllRestaurantsByUserCoordinatesResponse>() {}.getType();
            FindAllRestaurantsByUserCoordinatesResponse  returnValue = new ModelMapper().map(restaurant, objectType);
            returnValue.setDistance(distance);
            return returnValue;
        })).toList());
        response.sort((a, b) -> (int) ( Double.parseDouble(a.getDistance())  - Double.parseDouble(b.getDistance())));
        return response;
    }

    public ResponseEntity<Map<String,Restaurant>> save(CreateRestaurantDTO restaurant){
    try {
        final Restaurant record =  mapper.map(restaurant);
        restaurantRepository.save(record);
        final Map<String, Restaurant> response = new HashMap<>();
        response.put("restaurant",record);
        return ResponseEntity.ok(response);
    }catch (RuntimeException e){
        throw new DefaultException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    public ResponseEntity<Map<String,Restaurant>>one(Integer id){
    final Restaurant restaurant =    restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        final Map<String,Restaurant> response  = new HashMap<>();
        response.put("restaurant",restaurant);
        return  ResponseEntity.ok(response);

    }

    public ResponseEntity<Map<String,String>>update(UpdateRestaurantDTO restaurant, Integer id){
        restaurantRepository.findById(id).map(item -> {
                    item.setName(restaurant.getName());
                    item.setDescription(restaurant.getDescription());
                    item.setPhoto(restaurant.getPhoto());
                    return restaurantRepository.save(item);
                })
                .orElseGet(()->{
                    Restaurant toSave = updateMapper.map(restaurant);
                    return  restaurantRepository.save(toSave);
                });
               // .orElseThrow(() -> new RestaurantNotFoundException(id)); //  con esto arrojo error si no existe. es una forma de hacerlo
        final Map<String,String> response  = new HashMap<>();
        response.put("message","Updated was successfully");
        return  ResponseEntity.ok(response);

    }

    private Map<?,?> handleResponse(  String message, Object data){
        final Map<String, Object> response = new HashMap<>();
            response.put(message,data);
            return  response;
    }

    public  ResponseEntity<?> delete(Integer id){
      final Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
      if (restaurant == null) throw new RestaurantNotFoundException(id);
      restaurantRepository.deleteById(id);
      final Map<?,?> response = handleResponse("message","Restaurant deleted successfully!");
        return ResponseEntity.ok(response);

    }


    public ResponseEntity<?> all(){
        List<Restaurant> restaurants = (List<Restaurant>) restaurantRepository.findAll();
        final Map<?,?> response = handleResponse("restaurants",restaurants);
        return  ResponseEntity.ok(response);

    }

    @Transactional
    // para indicar el inicio de una transaccion, y que el metodo es transaccional, se usa con queries nativas
    public  ResponseEntity<?> updateLatitude(String latitude, Integer id){
        this.restaurantRepository.updateLatitude(latitude,id);
        final Map<?,?> response = handleResponse("message","update successfully!");
        return  ResponseEntity.ok(response);
    }


}
