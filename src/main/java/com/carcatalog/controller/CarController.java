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

    @DeleteMapping("{id}")
    public void deleteCar(@PathVariable int id) {
        carService.deleteCar(id);
    }

    @GetMapping("/id/{id}")
    public Car getCarByID(@PathVariable int id) {
        return carService.getCarByID(id);
    }

    @PatchMapping("{id}")
    public Car updateCar(@PathVariable int id, @RequestBody Car newCar) {
        return carService.updateCarByID(id, newCar);
    }

    @GetMapping("/brand/{name}")
    public List<Car> getCarsByBrand(@PathVariable String name) {
        return carService.getCarsByBrand(name);
    }

}
