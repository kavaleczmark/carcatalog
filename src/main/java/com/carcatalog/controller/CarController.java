package com.carcatalog.controller;

import com.carcatalog.model.Car;
import com.carcatalog.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cars")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping
    public Car insertCar(@RequestBody Car car) {
        return carService.setCar(car);
    }

}
