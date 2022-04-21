package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.impl.IUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService service;

    @Test
    public void registerTest() {
        User user = new User();
        user.setUsername("q");
        user.setPassword("123");
        service.register(user);
    }

    @Test
    public void loginTest() {
        System.out.println(service.login("poppy", "321"));
    }

    @Test
    public void changePasswordTest() {
        service.changePassword(4,"111", "321");
    }

    @Test
    public void changeInfoTest(){
        User user = new User();
        user.setUid(2);
        user.setEmail("tom@gmail.com");
        user.setGender(1);
        user.setPhone("130");
        service.changeInfo(2,user);
    }

    @Test
    public void changeAvatar() {
        service.changeAvatar(2, "avatar", "tom");
    }

    @Test
    public void md5Password() {
        IUserServiceImpl userService = new IUserServiceImpl();
        System.out.println(userService.md5Password("1", "123"));
    }
}
