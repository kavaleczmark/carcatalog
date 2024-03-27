package com.carcatalog.service;

import com.carcatalog.model.Brand;
import com.carcatalog.model.Car;
import com.carcatalog.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    public Car setCar(Car car) {
        return carRepository.save(car);
    }



}
