package com.example.mybatis;

import com.example.mybatis.db.mapper.CarMapper;
import com.example.mybatis.db.mapper.UserMapper;
import com.example.mybatis.db.entity.User;
import com.example.mybatis.dto.TransferCarDto;
import com.example.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private CarMapper carMapper;

    @Test
    public void getAll_shouldPass() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        List<User> users = Arrays.asList(user1, user2, user3);

        when(userMapper.getAll()).thenReturn(users);

        userService.getAll();

        verify(userMapper, times(1)).getAll();

        assertThat("Size is not equal to 3", userService.getAll().size(), is(3));
    }

    @Test
    public void getAllWithCars_shouldPass() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        List<User> users = Arrays.asList(user1, user2, user3);

        when(userMapper.getAllWithCars()).thenReturn(users);

        userService.getAllWithCars();

        verify(userMapper, times(1)).getAllWithCars();

        assertThat("Size is not equal to 3", userService.getAllWithCars().size(), is(3));
    }

    @Test
    public void getById_shouldPass() {
        userService.getById(1L);
        verify(userMapper, times(1)).getById(1L);
    }

    @Test
    public void create_shouldPass() {
        User user = new User();
        user.setFname("test");
        user.setLname("test");

        userService.create(user);

        verify(userMapper, times(1)).save(user);
    }

    @Test
    public void update_shouldPass() {
        User user = new User();
        user.setFname("test");
        user.setLname("test");

        userMapper.update(user);

        verify(userMapper, times(1)).update(user);
    }

    @Test
    public void delete_shouldPass() {
        userService.delete(1L);
        verify(userMapper, times(1)).delete(1L);
    }

    @Test
    public void buyCar_shouldPass() {
        userService.buyCar(1L, 1L);
        verify(userMapper, times(1)).buyCar(1L, 1L);
    }

    @Test
    public void sellCar_shouldPass() {
        userService.sellCar(1L, 1L);
        verify(userMapper, times(1)).sellCar(1L, 1L);
    }

    @Test
    public void transferCar_shouldPass() {
        TransferCarDto transferCarDto = new TransferCarDto();
        transferCarDto.setId_seller(1L);
        transferCarDto.setId_buyer(1L);
        transferCarDto.setId_car(1L);

        userService.transferCar(transferCarDto);

        verify(userMapper, times(1)).sellCar(1L, 1L);
        verify(userMapper, times(1)).buyCar(1L, 1L);
    }

    @Test
    public void isBelonging_shouldPass() {
        userService.isBelonging(1L);
        verify(userMapper, times(1)).isBelonging(1L);
    }


}
