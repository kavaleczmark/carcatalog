package tests.Brand;

import com.carcatalog.controller.BrandController;
import com.carcatalog.model.Brand;
import com.carcatalog.service.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrandControllerTest {

    @InjectMocks
    private BrandController brandController;

    @Mock
    private BrandService brandService;

    @Test
    public void testGetAllBrands() {
        List<Brand> brands = Arrays.asList(new Brand(1, "Brand1", "Country1"), new Brand(2, "Brand2", "Country2"));
        when(brandService.getAllBrands()).thenReturn(brands);
        List<Brand> result = brandController.getAllBrands();
        assertEquals(brands, result);
    }


    @Test
    public void testDeleteBrand() {
        int id = 1;
        brandController.deleteBrand(id);
        verify(brandService, times(1)).deleteBrand(id);
    }

    @Test
    public void testGetBrandByID() {
        int id = 1;
        Brand brand = new Brand(id, "Brand1", "Country1");
        when(brandService.getBrandByID(id)).thenReturn(brand);
        Brand result = brandController.getBrandByID(id);
        assertEquals(id, result.getId());
    }


    @Test
    public void testGetBrandsByCountry() {
        String country = "Country1";
        List<Brand> brands = Arrays.asList(new Brand(1, "Brand1", country), new Brand(2, "Brand2", country));
        when(brandService.getBrandsByCountry(country)).thenReturn(brands);
        List<Brand> result = brandController.getBrandsByCountry(country);
        assertEquals(brands, result);
    }

    @Test
    public void testUpdateBrand_OK() {
        int id = 1;
        Brand brand = new Brand(id, "Brand1", "Country1");
        Brand updatedBrand = new Brand(id, "UpdatedBrand1", "Country1");
        when(brandService.updateBrandByID(id, updatedBrand)).thenReturn(updatedBrand);
        Brand result = brandController.updateBrand(id, updatedBrand);
        assertEquals(updatedBrand, result);
        verify(brandService, times(1)).updateBrandByID(id, updatedBrand);
    }

    @Test
    public void testUpdateBrand_Conflict() {
        int id = 1;
        Brand brand = new Brand(id, "Brand1", "Country1");
        Brand updatedBrand = new Brand(id, "UpdatedBrand1", "Country2");
        when(brandService.updateBrandByID(id, updatedBrand)).thenReturn(null);
        brandController.updateBrand(id, updatedBrand);
        verify(brandService, times(1)).updateBrandByID(id, updatedBrand);
    }

    @Test
    public void testInsertBrand_OK() {
        Brand brand = new Brand(1, "Brand1", "Country1");
        when(brandService.setBrand(brand)).thenReturn(brand);
        Brand result = brandController.insertBrand(brand);
        assertEquals(brand, result);
        verify(brandService, times(1)).setBrand(brand);
    }

    @Test
    public void testInsertBrand_Conflict() {
        Brand brand = new Brand(1, "Brand1", "Country1");
        when(brandService.setBrand(brand)).thenReturn(null);
        brandController.insertBrand(brand);
        verify(brandService, times(1)).setBrand(brand);
    }
}