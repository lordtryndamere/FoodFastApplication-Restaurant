package com.liondevs.fastfood.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("/application.yml")
@SpringBootTest
public class RestaurantControllerTest {
    @DisplayName("findAllRestaurantsByUserCoordinates should return statusCode 200")
    @Test
    void findAllRestaurantsByUserCoordinatesTest_whenValidCoordinatesIsProvided_shouldReturnStatus200(){
        //Arrange

        //Act

        //Assert
    }
}
