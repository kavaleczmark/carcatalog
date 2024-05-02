package com.carcatalog.controller;

import com.carcatalog.model.Brand;
import com.carcatalog.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping
    public Brand insertBrand(@RequestBody Brand brand) {
        return brandService.setBrand(brand);
    }

    @DeleteMapping("{id}")
    public void deleteBrand(@PathVariable int id) {
        brandService.deleteBrand(id);
    }

    @GetMapping("/id/{id}")
    public Brand getBrandByID(@PathVariable int id) {
        return brandService.getBrandByID(id);
    }

    @PatchMapping("{id}")
    public Brand updateBrand(@PathVariable int id, @RequestBody Brand newBrand) {
        return brandService.updateBrandByID(id, newBrand);
    }

    @GetMapping("/country/{country}")
    public List<Brand> getBrandsByCountry(@PathVariable String country) {
        return brandService.getBrandsByCountry(country);
    }

}
