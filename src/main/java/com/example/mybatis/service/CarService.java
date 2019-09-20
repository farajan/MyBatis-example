package com.example.mybatis.service;

import com.example.mybatis.db.mapper.CarMapper;
import com.example.mybatis.db.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class contain all business logic for operations with cars.
 */
@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    /**
     * This function returns all cars from the database.
     *
     * @return A List of cars.
     */
    public List<Car> getAll() {
        return carMapper.getAll();
    }

    /**
     * This function returns specific car from the database.
     *
     * @param id An id of a car. This parameter can't be null.
     * @return A Cars.
     */
    public Car getById(Long id) {
        return carMapper.getById(id);
    }

    /**
     * This function returns specific cars from the database.
     *
     * @param ids A List of an ids of a cars. This parameter can't be null.
     * @return List of a cars.
     */
    public List<Car> getCarsByIds(List<Integer> ids) {
        return carMapper.getCarsByIds(ids);
    }

    /**
     * This function saves a car. Throw exception if a brand of a car is null.
     *
     * @param car A new car which will be saved. This parameter can't be null
     * @return A car which was saved to database.
     */
    public Car create(Car car) {
        if (car.getBrand() == null) {
            throw new IllegalArgumentException("Car must have a brand.");
        }
        carMapper.save(car);
        return car;
    }

    /**
     * This function updates a car. Throw exception if a id of car is null.
     *
     * @param car A car with new parameters. This parameter can't be null.
     * @return A updated car.
     */
    public Car update(Car car) {
        if (car.getId_car() == null) {
            throw new IllegalArgumentException("Car must have a ID.");
        }
        carMapper.update(car);
        return getById(car.getId_car());
    }

    /**
     * This function deletes a car. Throw exception if a car belongs someone.
     *
     * @param id An id of a car which will be deleted. This parameter can't be null.
     * @return A number of records which were successfully deleted.
     */
    public Integer delete(Long id) {
        if (isBelonging(id) != 0) {
            throw new IllegalArgumentException("This car belong someone.");
        }
        return carMapper.delete(id);
    }

    /**
     * This function checks if a car belongs to someone.
     *
     * @param id An id of a car. This parameter can't be null.
     * @return A number of users who a car belongs to.
     */
    public Integer isBelonging(Long id) {
        return carMapper.isBelonging(id);
    }
}
