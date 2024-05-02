package com.carcatalog.repository;

import com.carcatalog.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Optional<Car> findByName(String name);
    Optional<List<Car>> findByBrandId(int brandId);

}
