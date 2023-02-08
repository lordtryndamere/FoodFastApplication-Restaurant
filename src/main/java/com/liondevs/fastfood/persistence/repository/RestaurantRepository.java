package com.liondevs.fastfood.persistence.repository;

import com.liondevs.fastfood.persistence.entity.Restaurant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

   public   List<Restaurant> findAllByCreatedAt(Instant createdAt); // hay muchos queries de busqueda
    //queries nativos, con JPA y Hibernate
    @Modifying //indic que es una query de modification
    @Query(value = "UPDATE restaurant SET latitude=:latitude WHERE ID=:id", nativeQuery = true)
   public  void updateLatitude(@Param("latitude") String latitude,@Param("id") Integer id);

}
