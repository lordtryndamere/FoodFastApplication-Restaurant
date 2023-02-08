package com.liondevs.fastfood.controller.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Data
@Component
@Scope(scopeName = "stereotype")
public class CreateRestaurantDTO {
    @NotEmpty

    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String photo;
    @NotEmpty
    private String latitude;
    @NotEmpty
    private String longitude;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateRestaurantDTO that = (CreateRestaurantDTO) o;

        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(photo, that.photo)) return false;
        if (!Objects.equals(latitude, that.latitude)) return false;
        return Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }




}
