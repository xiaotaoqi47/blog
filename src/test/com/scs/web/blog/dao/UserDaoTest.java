package com.scs.web.blog.dao;

import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class UserDaoTest {
    private UserDao userDao = DaoFactory.getUserDaoInstance();


    @Test
    public void insertUser() throws SQLException {
        UserDto user = new UserDto();
        user.setNickname("111");
        user.setMobile("1391111111");
        user.setPassword("111");
        user.setBirthday(LocalDate.of(2010,10,10));
        userDao.insert(user);
    }

    @Test
    public void batchInsert() throws SQLException {
    }

    @Test
    public void findUserByMobile() throws SQLException {
        User user = userDao.findUserByMobile("13011112222");
        if (user != null) {

            System.out.println(user);
        } else {
            System.out.println("手机号不存在");
        }
    }

    @Test
    public void selectHotUsers() throws SQLException {
        List<User> userList = userDao.selectHotUsers();
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByKeywords() throws SQLException {
        List<User> userList = userDao.selectByKeywords("王");
        System.out.println(userList.size());
    }

    @Test
    public void update() throws SQLException {
        UserDto user = new UserDto();
        user.setNickname("wjh");
        user.setGender("男");
        user.setPassword("111");
        user.setIntroduction("我爱中国,江苏省,苏州市");
        user.setId((long) 23);
        int n = userDao.update(user);
        System.out.println(n);
    }
    @Test
    public void follow() throws SQLException {
        int n = userDao.follow(4, 0);
        System.out.println(n);
    }


}