package com.liondevs.fastfood.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor //genero automaticamente getter y setter
@Entity
@Table(name="restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    private String name;
    private String description;

    @Column(columnDefinition = "https://cwdaust.com.au/wpress/wp-content/uploads/2015/04/placeholder-restaurant.png")
    private String photo;

    private String latitude;

    private String longitude;

    @CreatedDate
    private Instant createdAt;
    @CreatedDate
    private Instant updatedAt;


    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Restaurant that = (Restaurant) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
