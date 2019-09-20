package com.example.mybatis.service;

import com.example.mybatis.db.mapper.CarMapper;
import com.example.mybatis.db.mapper.UserMapper;
import com.example.mybatis.db.entity.User;
import com.example.mybatis.dto.TransferCarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class contain all business logic for operations with users.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CarMapper carMapper;

    /**
     * This function returns all users from the database.
     *
     * @return A List of users.
     */
    public List<User> getAll() {
        return userMapper.getAll();
    }

    /**
     * This function return all users with their cars from the database.
     *
     * @return A List of users.
     */
    public List<User> getAllWithCars() {
        return userMapper.getAllWithCars();
    }

    /**
     * This function returns specific user from the database.
     *
     * @param id An id of a user. This parameter can't be null.
     * @return A user.
     */
    public User getById(Long id) {
        return userMapper.getById(id);
    }

    /**
     * This function saves a user. Throw exception if first name or last name is null.
     *
     * @param user A new user whi will be saved. This parameter can't be null
     * @return A user which was saved to database.
     */
    public User create(User user) {
        if (user.getFname() == null || user.getLname() == null) {
            throw new IllegalArgumentException("First name and last name are mandatory");
        }
        userMapper.save(user);
        return user;
    }

    /**
     * This function updates a user. Throw exception if id of user is null.
     *
     * @param user A user with new parameters. This parameter can't be null.
     * @return A updated user.
     */
    public User update(User user) {
        if (user.getId_user() == null) {
            throw new IllegalArgumentException("User ID is mandatory on update.");
        }
        userMapper.update(user);
        return getById(user.getId_user());
    }

    /**
     * This function deletes a user. Throw exception if a user own some cars.
     *
     * @param id An id of a user which will be deleted. This parameter can't be null.
     * @return A number of records which were successfully deleted.
     */
    public Integer delete(Long id) {
        if (isBelonging(id) != 0) {
            throw new IllegalArgumentException("This user haves some cars.");
        }
        return userMapper.delete(id);
    }

    /**
     * This function assigns a car to a user ownership.
     *
     * @param id_user An id of a user who will own a new car. This parameter can't be null
     * @param id_car  An id of a car which a user will own. This parameter can't be null
     * @return A number of record which were successfully inserted.
     */
    public Integer buyCar(Long id_user, Long id_car) {
        if (carMapper.isBelonging(id_car) != 0) {
            throw new IllegalArgumentException("This car belongs someone else.");
        }
        return userMapper.buyCar(id_user, id_car);
    }

    /**
     * This function remove a car for user ownership.
     *
     * @param id_user An id of a user who will be removed the car from. This parameter can't be null
     * @param id_car  id of a car which will be deleted. This parameter can't be null
     * @return A number of record which were successfully deleted.
     */
    public Integer sellCar(Long id_user, Long id_car) {
        return userMapper.sellCar(id_user, id_car);
    }

    /**
     * This function transfer a car from a owner to someone else. Throw exception if id of owner or new owner or car is null.
     *
     * @param transferCarDto A information about ids of owner, new owner and car.
     * @return A number of record which were successfully added.
     */
    @Transactional
    public Integer transferCar(TransferCarDto transferCarDto) {
        if (transferCarDto.getId_seller() == null || transferCarDto.getId_buyer() == null || transferCarDto.getId_car() == null) {
            throw new IllegalArgumentException("IDs for seller, buyer and car are mandatory.");
        }
        return (
            userMapper.sellCar(
                    transferCarDto.getId_seller(),
                    transferCarDto.getId_car()
            )
            &
            userMapper.buyCar(
                    transferCarDto.getId_buyer(),
                    transferCarDto.getId_car()
            )
        );

    }

    /**
     * This function checks if a user own someone cars.
     *
     * @param id An id of a user. This parameter can't be null.
     * @return A number of cars which a user own.
     */
    public Integer isBelonging(Long id) {
        return userMapper.isBelonging(id);
    }
}
