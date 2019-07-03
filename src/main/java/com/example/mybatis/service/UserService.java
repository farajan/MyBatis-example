package com.example.mybatis.service;

import com.example.mybatis.exception.MyException;
import com.example.mybatis.mapper.CarMapper;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.model.db.User;
import com.example.mybatis.model.payload.TransferCar;
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
     * @return A number of a records which were successfully saved.
     */
    public Integer save(User user) {
        if (user.getFname() == null || user.getLname() == null) {
            try {
                throw new MyException("First name and last name are mandatory");
            } catch (MyException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return userMapper.save(
                user.getFname(),
                user.getLname(),
                user.getAge()
        );
    }

    /**
     * This function updates a user. Throw exception if id of user is null.
     *
     * @param user A user with new parameters. This parameter can't be null.
     * @return A number of records which were successfully updated.
     */
    public Integer update(User user) {
        if(user.getId_user() == null) {
            try {
                throw new MyException("User ID is mandatory on update.");
            } catch (MyException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return userMapper.update(
                user.getId_user(),
                user.getFname(),
                user.getLname(),
                user.getAge()
        );
    }

    /**
     * This function deletes a user. Throw exception if a user own some cars.
     *
     * @param id An id of a user which will be deleted. This parameter can't be null.
     * @return A number of records which were successfully deleted.
     */
    public Integer delete(Long id) {
        if(isBelonging(id) != 0) {
            try {
                throw new MyException("This user haves some cars.");
            } catch (MyException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return userMapper.delete(id);
    }

    /**
     * This function assigns a car to a user ownership.
     *
     * @param id_user An id of a user who will own a new car. This parameter can't be null
     * @param id_car An id of a car which a user will own. This parameter can't be null
     * @return A number of record which were successfully inserted.
     */
    public Integer buyCar(Long id_user, Long id_car) {
        if(carMapper.isBelonging(id_car) != 0) {
            try {
                throw new MyException("This car belongs someone else.");
            } catch (MyException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return userMapper.buyCar(id_user, id_car);
    }

    /**
     * This function remove a car for user ownership.
     *
     * @param id_user An id of a user who will be removed the car from. This parameter can't be null
     * @param id_car id of a car which will be deleted. This parameter can't be null
     * @return A number of record which were successfully deleted.
     */
    public Integer sellCar(Long id_user, Long id_car) {
        return userMapper.sellCar(id_user, id_car);
    }

    /**
     * This function transfer a car from a owner to someone else. Throw exception if id of owner or new owner or car is null.
     *
     * @param transferCar A information about ids of owner, new owner and car.
     * @return A number of record which were successfully added.
     */
    @Transactional
    public Integer transferCar(TransferCar transferCar) {
        if(transferCar.getId_seller() == null || transferCar.getId_buyer() == null || transferCar.getId_car() == null) {
            try {
                throw new MyException("IDs for seller, buyer and car are mandatory.");
            } catch (MyException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return (
                userMapper.sellCar(
                    transferCar.getId_seller(),
                    transferCar.getId_car()
                )
                &
                userMapper.buyCar(
                    transferCar.getId_buyer(),
                    transferCar.getId_car()
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
