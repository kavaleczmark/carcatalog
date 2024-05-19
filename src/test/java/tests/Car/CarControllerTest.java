package tests.Car;

import com.carcatalog.controller.CarController;
import com.carcatalog.model.Car;
import com.carcatalog.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    @InjectMocks
    private CarController carController;

    @Mock
    private CarService carService;

    @Test
    public void testGetAllCars() {
        // Arrange
        List<Car> cars = Arrays.asList(new Car(1, 1, "Audi", "1993-"), new Car(2, 2, "Bmw", "1993-"));
        when(carService.getAllCars()).thenReturn(cars);

        // Act
        List<Car> result = carController.getAllCars();

        // Assert
        assertEquals(cars, result);
    }

    @Test
    public void testInsertCar() {
        // Arrange
        Car car = new Car(1, 1, "Audi", "2000");
        when(carService.setCar(car)).thenReturn(car);

        // Act
        Car result = carController.insertCar(car);

        // Assert
        assertEquals(car, result);
    }
    @Test
    public void testGetCarByID() {
        // Arrange
        int id = 1;
        Car car = new Car(id, 1, "Audi", "1993-");
        when(carService.getCarByID(id)).thenReturn(car);

        // Act
        Car result = carController.getCarByID(id);

        // Assert
        assertEquals(car, result);
    }

    @Test
    public void testUpdateCar() {
        // Arrange
        int id = 1;
        Car car = new Car(id, 1, "Audi", "1993-");
        when(carService.updateCarByID(id, car)).thenReturn(car);

        // Act
        Car result = carController.updateCar(id, car);

        // Assert
        assertEquals(car, result);
    }

    @Test
    public void testGetCarsByBrand() {

        String name = "Audi";
        List<Car> cars = Arrays.asList(new Car(1, 1, "Audi", "1993-"), new Car(2, 2, "Bmw", "1993-"));
        when(carService.getCarsByBrand(name)).thenReturn(cars);
        List<Car> result = carController.getCarsByBrand(name);
        assertEquals(cars, result);
    }
    @Test
    public void testDeleteCar() {
        int id = 1;
        Car car = new Car(id, 1, "Audi", "1993-");
        carController.insertCar(car);
        carController.deleteCar(id);
        assertEquals(0,carController.getAllCars().size());
    }
}