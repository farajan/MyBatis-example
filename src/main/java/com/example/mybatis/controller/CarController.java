package com.example.mybatis.controller;

import com.example.mybatis.model.db.Car;
import com.example.mybatis.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class provides REST methods with regards to cars for a client.
 */
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * This method provides get List of all cars from the database.
     *
     * @return A List of cars.
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Car> getAll() {
        return carService.getAll();
    }

    /**
     * This method provides get specific car.
     *
     * @param id An id of a car.
     * @return A car.
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public Car getById(@RequestParam("id") Long id) {
        return carService.getById(id);
    }

    /**
     * This method provides get specific List of cars from the database.
     *
     * @param ids A List of ids.
     * @return A List of cars.
     */
    @RequestMapping(value = "/getCarsByIds", method = RequestMethod.POST)
    public List<Car> getCarsByIds(@RequestBody List<Integer> ids) {
        return carService.getCarsByIds(ids);
    }

    /**
     * This method provides save a car to the database.
     *
     * @param car A new car which will be saved.
     * @return A number of a records which were successfully saved.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Integer save(@RequestBody Car car) {
        return carService.save(car);
    }

    /**
     * This function updates parameters of a car in the database.
     *
     * @param car A car with new parameters.
     * @return A number of a records which were successfully saved.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Integer update(@RequestBody Car car) {
        return carService.update(car);
    }

    /**
     * This function deletes car from the database.
     *
     * @param id An id of a car which will be deleted.
     * @return A number of a records which were successfully saved.
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Integer delete(@RequestParam("id") Long id) {
        return carService.delete(id);
    }

}
