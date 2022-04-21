package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert() {
        User user1 = new User();
        user1.setUsername("test");
        user1.setPassword("test");
        userMapper.insert(user1);
    }

    @Test
    public void findUserByUsername() {
        System.out.println(userMapper.findUserByUsername("test"));
    }

    @Test
    public void loginTest(){
        User user1 = new User();
        user1.setUsername("test");
        user1.setPassword("test");
        System.out.println(userMapper.login("test", "test"));
    }

    @Test
    public void updatePasswordTest(){
        String password = "321";
        System.out.println(userMapper.updatePassword(password, 4, new Date(), "admin"));
    }

    @Test
    public void findUserByUid() {
        System.out.println(userMapper.findUserByUid(4));
    }

    @Test
    public void updateInfoByUidTest(){
        User user = new User();
        user.setEmail("tom@qq.com");
        user.setPhone("1101");
        user.setGender(1);
        user.setUid(2);
        user.setModifiedUser("Admin");
        user.setModifiedTime(new Date());
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateAvatar() {
        int result = userMapper.updateAvatar("/update/avatar", 2, "Admin", new Date());
        System.out.println(result == 1 ? "OK" : "fail");
    }
}
