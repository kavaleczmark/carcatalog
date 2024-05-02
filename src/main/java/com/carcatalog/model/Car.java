package com.carcatalog.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Data
@Table(name = "Cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brand_id")
    private int brandId;

    private String name;

    @Column(name = "year_of_manufacture")
    private String yearOfManufacture;

}
