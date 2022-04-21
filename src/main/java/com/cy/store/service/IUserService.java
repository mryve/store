package com.cy.store.service;

import com.cy.store.entity.User;

public interface IUserService {
    void register(User user);

    User login(String username, String password);

    User findUserByUsername(String username);
    void changePassword(int uid, String oldPassword, String newPassword);
    void changeInfo(Integer uid, User user);
    void changeAvatar(Integer uid, String avatar, String username);
}
