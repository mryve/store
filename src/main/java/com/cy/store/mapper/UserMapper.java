package com.cy.store.mapper;

import com.cy.store.entity.User;

import java.util.Date;

public interface UserMapper {
    Integer insert(User user);
    User findUserByUsername(String username);
    User findUserByUid(Integer uid);
    User login(String username, String password);
    Integer updatePassword(String password, Integer uid, Date modifiedTime, String modifiedUser);
    Integer updateInfoByUid(User user);
    Integer updateAvatar(String avatar, Integer uid, String modifiedUser, Date modifiedTime);
}
