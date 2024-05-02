package com.carcatalog.service;

import com.carcatalog.exception.ResourceNotFoundException;
import com.carcatalog.model.Car;
import com.carcatalog.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BrandService brandService;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    public Car setCar(Car car) {
        if (carRepository.findByName(car.getName()).isPresent()) {
            throw new RuntimeException("This name is already present!");
        }
        return carRepository.save(car);
    }


    public void deleteCar(int id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID not found"));
        carRepository.delete(car);
    }

    public Car getCarByID(int id) {
        return carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID not found"));
    }

    public Car updateCarByID(int id, Car newCar) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID not found"));
        if (carRepository.findByName(newCar.getName()).isPresent()) {
            throw new RuntimeException("This name is already present!");
        }
        car.setName(newCar.getName());
        car.setBrandId(newCar.getBrandId());
        car.setYearOfManufacture(newCar.getYearOfManufacture());
        carRepository.save(car);
        return car;
    }

    public List<Car> getCarsByBrand(String name) {
        int brandId = brandService.getBrandByName(name).getId();
        return carRepository.findByBrandId(brandId).orElseThrow(() -> new ResourceNotFoundException("No cars found with provided name"));
    }

}
