package com.liondevs.fastfood.service;

import com.liondevs.fastfood.context.ConfigProperties;
import com.liondevs.fastfood.controller.dtos.CreateRestaurantDTO;
import com.liondevs.fastfood.controller.dtos.UpdateRestaurantDTO;
import com.liondevs.fastfood.exceptions.DefaultException;
import com.liondevs.fastfood.exceptions.RestaurantNotFoundException;
import com.liondevs.fastfood.mapper.CreateRestaurantInDTOToRestaurant;
import com.liondevs.fastfood.mapper.UpdateRestaurantInDTOtoRestaurant;
import com.liondevs.fastfood.persistence.entity.Restaurant;
import com.liondevs.fastfood.persistence.repository.RestaurantRepository;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class RestaurantService {
     //inyeccion por anotacion @AutoWired
  private final  RestaurantRepository restaurantRepository;
  private final CreateRestaurantInDTOToRestaurant mapper;
  private final UpdateRestaurantInDTOtoRestaurant updateMapper;

  private final ConfigProperties configProperties;

   public  RestaurantService(RestaurantRepository restaurantRepository, CreateRestaurantInDTOToRestaurant mapper, UpdateRestaurantInDTOtoRestaurant updateMapper, ConfigProperties configProperties){

       this.restaurantRepository = restaurantRepository; //inyeccion por constructor, mejor para testear
       this.mapper = mapper;
       this.updateMapper = updateMapper;


       this.configProperties = configProperties;
   }
    //con esto leo valores del application properties
//    @Value("#{${name}.name}")
//    public String name;
//
//    @Value("#{${name}.description}")
//    public String description;


    public ResponseEntity<Map<String,Restaurant>> save(CreateRestaurantDTO restaurant){
        System.out.println(configProperties.getNames().get("name")); //llamando propiedades del archivo de configuracion
        System.out.println(configProperties.getNames().get("description"));

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
