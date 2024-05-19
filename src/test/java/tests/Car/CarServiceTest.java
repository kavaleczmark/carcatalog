package tests.Car;

import com.carcatalog.exception.ResourceNotFoundException;
import com.carcatalog.model.Brand;
import com.carcatalog.model.Car;
import com.carcatalog.repository.CarRepository;
import com.carcatalog.service.CarService;
import com.carcatalog.service.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private BrandService brandService;

    @Test
    public void testGetAllCars() {
        // Arrange
        List<Car> cars = Arrays.asList(new Car(1, 1, "Audi", "1993-"), new Car(2, 2, "Bmw", "1993-"));

        // Act
        when(carRepository.findAll()).thenReturn(cars);
        List<Car> result = carService.getAllCars();

        // Assert
        assertEquals(cars, result);
    }

    @Test
    public void testSetCar() {
        // Arrange
        Car car = new Car(1, 1, "Audi", "2000");

        // Act
        when(carRepository.save(car)).thenReturn(car);
        Car result = carService.setCar(car);

        // Assert
        assertEquals(car, result);
    }

    @Test
    public void testGetCarByID() {
        // Arrange
        int id = 1;
        Car car = new Car(1, 1, "Audi", "2000");

        // Act
        when(carRepository.findById(id)).thenReturn(Optional.of(car));
        Car result = carService.getCarByID(id);

        // Assert
        assertEquals(car, result);
    }

    @Test
    public void testUpdateCarByID() {
        // Arrange
        int id = 1;
        Car car = new Car(1, 1, "Audi", "2000");
        Car updatedCar = new Car(1, 1, "Audi A6", "2012");

        // Act
        when(carRepository.findById(id)).thenReturn(Optional.of(car));
        when(carRepository.save(car)).thenReturn(updatedCar);
        Car result = carService.updateCarByID(id, updatedCar);

        // Assert
        assertEquals(updatedCar, result);
    }
    @Test
    public void testGetCarsByBrand() {
        // Arrange
        String name = "Audi";
        List<Car> cars = Arrays.asList(new Car(1, 1, "Audi", "1993-"), new Car(2, 2, "Bmw", "1993-"));

        // Act
        when(brandService.getBrandByName(name)).thenReturn(new Brand(1, "Audi", "Germany"));
        when(carRepository.findByBrandId(brandService.getBrandByName(name).getId())).thenReturn(Optional.of(cars));

        // Act
        List<Car> result = carService.getCarsByBrand(name);

        // Assert
        verify(carRepository).findByBrandId(1);
        assertEquals(cars, result);
    }

    @Test
    public void testDeleteCar() {
        // Arrange
        int id = 1;
        Car car = new Car(id, 1, "Audi", "2010");
        when(carRepository.findById(id)).thenReturn(Optional.of(car));
        carService.deleteCar(id);
        // Assert
        assertEquals(0,carService.getAllCars().size());
    }
    @Test
    public void testDeleteCarNotFound() {
        // Arrange
        int id = 1;

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> carService.deleteCar(id));
    }
    @Test
    void testUpdateCarByID_NameAlreadyExists() {
        // Arrange
        int existingCarId = 1;
        String existingCarName = "Existing Car";
        String newCarName = "New Car";

        Car existingCar = new Car(existingCarId, 1, existingCarName, "2020");
        when(carRepository.findById(existingCarId)).thenReturn(Optional.of(existingCar));
        when(carRepository.findByName(newCarName)).thenReturn(Optional.of(existingCar));

        Car newCar = new Car(existingCarId, 1, newCarName, "2021");

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            carService.updateCarByID(existingCarId, newCar);
        });
    }

    @Test
    void testSetCar_NameAlreadyExists() {
        // Arrange
        String existingCarName = "Existing Car";
        Car existingCar = new Car(1, 1, existingCarName, "2020");
        when(carRepository.findByName(existingCarName)).thenReturn(Optional.of(existingCar));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            carService.setCar(existingCar);
        });
    }

}