package com.carcatalog.service;

import com.carcatalog.exception.ResourceNotFoundException;
import com.carcatalog.model.Brand;
import com.carcatalog.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand setBrand(Brand brand) {
        if (brandRepository.findByName(brand.getName()).isPresent()) {
            throw new RuntimeException("This name is already present!");
        }
        return brandRepository.save(brand);
    }

    public void deleteBrand(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID not found"));
        brandRepository.delete(brand);
    }

    public Brand getBrandByID(int id) {
        return brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID not found"));
    }

    public Brand updateBrandByID(int id, Brand newBrand) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID not found"));
        if (brandRepository.findByName(newBrand.getName()).isPresent()) {
            throw new RuntimeException("This name is already present!");
        }
        brand.setName(newBrand.getName());
        brand.setCountry(newBrand.getCountry());
        brandRepository.save(brand);
        return brand;
    }

    public List<Brand> getBrandsByCountry(String country) {
        return brandRepository.findByCountry(country).orElseThrow(() -> new ResourceNotFoundException("No brands found with the provided country"));
    }

    public Brand getBrandByName(String name) {
        return brandRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("No brands found with the provided name"));
    }

}
