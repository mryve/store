package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User user = mapper.findUserByUid(uid);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        int result = mapper.updateAvatar(avatar, uid, username, new Date());
        if (result != 1) {
            throw new UpdateException("Unknown Exception happened");
        }
    }

    @Override
    public void changeInfo(Integer uid, User user) {
        User user1 = mapper.findUserByUid(uid);
        if (user1 == null) {
            throw new UserNotFoundException("This user is not exist");
        }
        user.setUid(uid);
        user.setUsername(user1.getUsername());
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        int result = mapper.updateInfoByUid(user);
        if (result != 1) {
            throw new UpdateException("Unknown exception happened");
        }
    }

    @Override
    public void changePassword(int uid, String oldPassword, String newPassword) {
        User user = mapper.findUserByUid(uid);
        if (user.getIsDelete() == 1 || user == null) {
            throw new UserNotFoundException("This user is not exist");
        }
        String oldMd5Password = md5Password(oldPassword, user.getSalt());
        if (!user.getPassword().equals(oldMd5Password)) {
            throw new PasswordException("Old password is incorrect");
        }
        if (newPassword == null || newPassword == "") {
            throw new PasswordException("New password cannot be null");
        }
        String newMd5Password = md5Password(newPassword, user.getSalt());
        int result = mapper.updatePassword(newMd5Password, uid, new Date(), user.getUsername() );
        if (result != 1) {
            throw new UpdateException("Unknown update exception happened");
        }
    }

    @Override
    public User login(String username, String password) {
        User user = findUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("This user is not exist");
        }
        String salt = user.getSalt();
        String md5Password = md5Password(password, salt);
        if(!md5Password.equals(user.getPassword())){
            throw new PasswordException("Password is wrong");
        }
        if (user.getIsDelete() == 1) {
            throw new UserNotFoundException("This user is not exist");
        }
        User result = new User();
        result.setUsername(username);
        result.setUid(user.getUid());
        result.setAvatar(user.getAvatar());
        return result;
    }

    @Override
    public User findUserByUsername(String username) {
        return mapper.findUserByUsername(username);
    }

    @Override
    public void register(User user) {
        String username = user.getUsername();
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = md5Password(oldPassword, salt);
        User findUser = mapper.findUserByUsername(username);
        if (findUser != null) {
            throw new UsernameDuplicatedException("This user already exist");
        }

        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setIsDelete(0);
        user.setCreatedTime(new Date());
        user.setCreatedUser(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        int result = mapper.insert(user);
        if (result != 1) {
            throw new InsertException("Unknown register exception occurs");
        }
    }

    public String md5Password(String password, String salt) {
        String md5Password = null;
        for (int i = 0; i < 3; i++) {
            md5Password = (DigestUtils.md5DigestAsHex((salt + password + salt).getBytes())).toUpperCase();
        }
        return md5Password;
    }
}
