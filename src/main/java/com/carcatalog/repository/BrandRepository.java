package com.carcatalog.repository;

import com.carcatalog.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer>, JpaSpecificationExecutor<Brand> {

    Optional<Brand> findByName(String name);

    Optional<List<Brand>> findByCountry(String country);

}