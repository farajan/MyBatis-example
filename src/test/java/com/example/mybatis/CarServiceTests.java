package com.example.mybatis;

import com.example.mybatis.db.mapper.CarMapper;
import com.example.mybatis.db.entity.Car;
import com.example.mybatis.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTests {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarMapper carMapper;

    @Test
    public void getAll_shouldPass() {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        List<Car> cars = Arrays.asList(car1, car2, car3);

        when(carMapper.getAll()).thenReturn(cars);

        carService.getAll();

        verify(carMapper, times(1)).getAll();

        assertThat("Size is not equal to 3", carService.getAll().size(), is(3));
    }

    @Test
    public void getById_shouldPass() {
        carService.getById(1L);
        verify(carMapper, times(1)).getById(1L);
    }

    @Test
    public void getCarsByIds_shouldPass() {
        Car car1 = new Car();
        car1.setId_car(1L);
        Car car2 = new Car();
        car2.setId_car(2L);
        Car car3 = new Car();
        car3.setId_car(3L);
        List<Car> cars = Arrays.asList(car1, car2, car3);
        List<Integer> ids = new ArrayList<>(Arrays.asList(1, 2, 3));

        when(carMapper.getCarsByIds(ids)).thenReturn(cars);

        carService.getCarsByIds(ids);

        verify(carMapper, times(1)).getCarsByIds(ids);

        assertThat("Size is not equal to 3", carService.getCarsByIds(ids).size(), is(3));
    }

    @Test
    public void create_shouldPass() {
        Car car = new Car();
        car.setBrand("test");
        car.setHorsepower(0);
        carService.create(car);
        verify(carMapper, times(1)).save(car);
    }

    @Test
    public void delete_shouldPass() {
        carService.delete(1L);
        verify(carMapper, times(1)).delete(1L);
    }

    @Test
    public void isBelonging() {
        carService.isBelonging(1L);
        verify(carMapper, times(1)).isBelonging(1L);
    }

}
