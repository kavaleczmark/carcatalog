package tests.Brand;

import com.carcatalog.exception.ResourceNotFoundException;
import com.carcatalog.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.carcatalog.model.Brand;
import com.carcatalog.service.BrandService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    @Test
    public void testGetAllBrands() {
        List<Brand> brands = List.of(new Brand(1, "Toyota", "Japan"), new Brand(2, "Ford", "USA"));
        when(brandRepository.findAll()).thenReturn(brands);
        List<Brand> result = brandService.getAllBrands();
        assertEquals(brands, result);
        assertNotNull(result);
    }

    @Test
    public void testSetBrand() {
        Brand brand = new Brand(1, "Toyota", "Japan");
        when(brandRepository.save(brand)).thenReturn(brand);
        Brand result = brandService.setBrand(brand);
        assertEquals(brand, result);
    }

    @Test
    public void testDeleteBrand() {
        int id = 1;
        when(brandRepository.findById(id)).thenReturn(Optional.of(new Brand(id, "Toyota", "Japan")));
        brandService.deleteBrand(id);
    }

    @Test
    public void testGetBrandByID() {
        int id = 1;
        Brand brand = new Brand(id, "Toyota", "Japan");
        when(brandRepository.findById(id)).thenReturn(Optional.of(brand));
        Brand result = brandService.getBrandByID(id);
        assertEquals(brand, result);
    }

    @Test
    public void testUpdateBrand() {
        int id = 1;
        Brand brand = new Brand(id, "Toyota", "Japan");
        Brand newBrand = new Brand(id, "Toyota", "Japan");
        when(brandRepository.findById(id)).thenReturn(Optional.of(brand));
        Brand result = brandService.updateBrandByID(id, newBrand);
        assertEquals(brand, result);
    }
    @Test
    void testSetBrand_NameAlreadyExists() {
        // Arrange
        String existingName = "Existing Brand";
        Brand existingBrand = new Brand(1, existingName, "Country");
        when(brandRepository.findByName(existingName)).thenReturn(Optional.of(existingBrand));

        Brand newBrand = new Brand(2, existingName, "New Country");

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            brandService.setBrand(newBrand);
        });
    }
    @Test
    void testUpdateBrandByID_NameAlreadyExists() {
        // Arrange
        int existingBrandId = 1;
        String existingBrandName = "Existing Brand";
        String newBrandName = "New Brand";

        Brand existingBrand = new Brand(existingBrandId, existingBrandName, "Country");
        when(brandRepository.findById(existingBrandId)).thenReturn(Optional.of(existingBrand));
        when(brandRepository.findByName(newBrandName)).thenReturn(Optional.of(existingBrand)); // Ezt kell hozzáadni

        Brand newBrand = new Brand(existingBrandId, newBrandName, "New Country");

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            brandService.updateBrandByID(existingBrandId, newBrand);
        });
    }


    @Test
    public void testGetBrandsByCountry() {
        String country = "Japan";
        List<Brand> brands = List.of(new Brand(1, "Toyota", country), new Brand(2, "Honda", country));
        when(brandRepository.findByCountry(country)).thenReturn(Optional.of(brands));
        List<Brand> result = brandService.getBrandsByCountry(country);
        assertEquals(brands, result);
    }

    @Test
    public void testGetBrandByName() {
        String name = "Toyota";
        Brand brand = new Brand(1, name, "Japan");
        when(brandRepository.findByName(name)).thenReturn(Optional.of(brand));
        Brand result = brandService.getBrandByName(name);
        assertEquals(brand, result);
    }
}