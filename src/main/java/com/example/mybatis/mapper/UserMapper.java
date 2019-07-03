package com.example.mybatis.mapper;

import com.example.mybatis.model.db.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface maps methods with SQL queries.
 * SQL queries are define in resources/mapper/UserMapper.xml for this interface.
 */
@Repository
@Mapper
public interface UserMapper {

    /**
     * This function finds all users who are saved in the database.
     *
     * @return A List of the users.
     */
    List<User> getAll();

    /**
     * This function finds specific user in the database.
     *
     * @param id An id of the user. This parameter can't be null.
     * @return A user.
     */
    User getById(Long id);

    /**
     * This function finds all users with their cars that are saved in the database.
     *
     * @return A List of users.
     */
    List<User> getAllWithCars();

    /**
     * This function saves user to the database.
     *
     * @param fname A first name of a new user. This parameter can't be null.
     * @param lname A last name of a new user. This parameter can't be null.
     * @param age A age of a new user. This parameter can be null.
     * @return A number of records which were successfully saved.
     */
    Integer save(@Param("fname") String fname, @Param("lname") String lname, @Param("age") Integer age);

    /**
     * This function update user record in the database.
     *
     * @param id An id of the user which will be updated. This parameter can't be null.
     * @param fname A new first name of the user. This parameter can be null.
     * @param lname A new last name of the user. This parameter can be null.
     * @param age A new age of the user. This parameter can be null.
     * @return A number of records which were successfully updated.
     */
    Integer update(@Param("id") Long id, @Param("fname") String fname, @Param("lname") String lname, @Param("age") Integer age);

    /**
     * This function deletes specific user in the database.
     *
     * @param id An id of the user who will be deleted. This parameter can't be null.
     * @return A number of records which were successfully deleted.
     */
    Integer delete(Long id);

    /**
     * This function assigns a car to a user ownership.
     *
     * @param id_user An id of a user who will own a new car. This parameter can't be null
     * @param id_car An id of a car which a user will own. This parameter can't be null
     * @return A number of record which were successfully inserted.
     */
    Integer buyCar(@Param("id_user") Long id_user, @Param("id_car") Long id_car);

    /**
     * This function remove a car for user ownership.
     *
     * @param id_user An id of a user who will be removed the car from. This parameter can't be null
     * @param id_car id of a car which will be deleted. This parameter can't be null
     * @return A number of record which were successfully deleted.
     */
    Integer sellCar(@Param("id_user") Long id_user, @Param("id_car") Long id_car);

    /**
     * This function checks if a user have some cars.
     *
     * @param id An id of a car. This parameter can't be null.
     * @return A number of cars which a user own.
     */
    Integer isBelonging(Long id);
}
