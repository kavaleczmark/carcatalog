package com.carcatalog.specification;

import com.carcatalog.model.Brand;
import com.carcatalog.model.Car;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class CarSpecifications {
    public static Specification<Car> hasBrandName(String name) {
        return (root, query, criteriaBuilder) -> {
            Join<Car, Brand> carBrandJoin = root.join("Brands");
            return criteriaBuilder.like(carBrandJoin.get("name"),"%"+name+"%");
        };

    }
}
