package com.example.mybatis.db.mapper;

import com.example.mybatis.db.entity.Car;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface maps methods with SQL queries.
 * SQL queries are define in resources/mapper/CarMapper.xml for this interface.
 */
@Repository
@Mapper
public interface CarMapper {

    /**
     * This function finds all cars which are saved in the database.
     *
     * @return A List of a cars.
     */
    List<Car> getAll();

    /**
     * This function finds specific car in the database.
     *
     * @param id An id of a car. This parameter can't be null.
     * @return A car.
     */
    Car getById(Long id);

    /**
     * This function finds specific cars in the database.
     *
     * @param ids A List of an ids of a cars. This parameter can't be null.
     * @return List of a cars.
     */
    List<Car> getCarsByIds(List<Integer> ids);

    /**
     * This function saves car to the database.
     *
     * @param car A new car. This parameter can't be null.
     * @return A number of records which were successfully saved.
     */
    Integer save(Car car);

    /**
     * This function update car record in the database.
     *
     * @param car A car with new parameters. This parameter can't be null.
     * @return A number of records which were successfully updated.
     */
    Integer update(Car car);

    /**
     * This function deletes specific car in the database.
     *
     * @param id An id of a car which will be deleted. This parameter can't be null.
     * @return A number of records which were successfully deleted.
     */
    Integer delete(Long id);

    /**
     * This function checks if a car belongs to someone.
     *
     * @param id An id of a car. This parameter can't be null.
     * @return A number of users who a car belongs to.
     */
    Integer isBelonging(Long id);
}
