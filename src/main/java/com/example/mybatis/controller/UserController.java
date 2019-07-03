package com.example.mybatis.controller;

import com.example.mybatis.model.db.User;
import com.example.mybatis.model.payload.TransferCar;
import com.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class provides REST methods with regards to users for a client.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * This function provides get List of all users from the database.
     *
     * @return A List of users.
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

    /**
     * This function provides get List of all users with their cars from the database.
     *
     * @return A List of users.
     */
    @RequestMapping(value = "/getAllWithCars", method = RequestMethod.GET)
    public List<User> getAllWithCars() {
        return userService.getAllWithCars();
    }

    /**
     * This function provides get specific user.
     *
     * @param id An id of a user.
     * @return A users.
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public User getById(@RequestParam("id") Long id) {
        return userService.getById(id);
    }

    /**
     * This function provides save a user to the database.
     *
     * @param user A new user which will be saved.
     * @return A number of a records which were successfully saved.
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Integer save(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * This function provide update parameters of a user in the database.
     *
     * @param user A user with new parameters.
     * @return A number of a records which were successfully saved.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Integer update(@RequestBody User user) {
        return userService.update(user);
    }

    /**
     * This function provides delete user from the database.
     *
     * @param id An id of a user who will be deleted.
     * @return A number of a records which were successfully saved.
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Integer delete(@RequestParam("id") Long id) {
        return userService.delete(id);
    }

    /**
     * This function provide assign a car to a user ownership.
     *
     * @param id_user An id of a user who will own a new car.
     * @param id_car An id of a car which a user will own.
     * @return A number of record which were successfully inserted.
     */
    @RequestMapping(value = "/buyCar", method = RequestMethod.POST)
    public Integer buyCar(@RequestParam("id_user") Long id_user, @RequestParam("id_car") Long id_car) {
        return userService.buyCar(id_user, id_car);
    }

    /**
     * This function provide remove a car for user ownership.
     *
     * @param id_user An id of a user who will be removed the car from.
     * @param id_car id of a car which will be deleted.
     * @return A number of record which were successfully deleted.
     */
    @RequestMapping(value = "/sellCar", method = RequestMethod.DELETE)
    public Integer sellCar(@RequestParam("id_user") Long id_user, @RequestParam("id_car") Long id_car) {
        return userService.sellCar(id_user, id_car);
    }

    /**
     * This function provides transfer a car from a owner to someone else.
     *
     * @param transferCar A information about ids of owner, new owner and car.
     * @return A number of record which were successfully added.
     */
    @RequestMapping(value = "/transferCar", method = RequestMethod.POST)
    public Integer transferCar(@RequestBody TransferCar transferCar) {
        return userService.transferCar(transferCar);
    }

}
