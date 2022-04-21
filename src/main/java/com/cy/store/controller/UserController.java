package com.cy.store.controller;

import com.cy.store.controller.ex.FileEmptyException;
import com.cy.store.controller.ex.FileIOException;
import com.cy.store.controller.ex.FileSizeException;
import com.cy.store.controller.ex.FileTypeException;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.util.JsonResult;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    private IUserService service;

    @RequestMapping("/register")
    public JsonResult<Void> register(User user) {
        service.register(user);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/login")
    public JsonResult<User> login(HttpSession session, String username, String password) {
        User user = service.login(username, password);
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", username);
        return new JsonResult<User>(OK,user);
    }

    @RequestMapping("/changePassword")
    public JsonResult<Void> changePassword(HttpSession session, String oldPassword, String newPassword) {
        int uid = getUidFromSession(session);
        service.changePassword(uid,oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/changeInfo")
    public JsonResult<Void> changeInfo(HttpSession session, User user) {
        int uid = getUidFromSession(session);
        service.changeInfo(uid, user);
        System.out.println(uid + user.getGender() + user.getEmail() + user.getPhone());
        return new JsonResult<>(OK);
    }

    @RequestMapping("/get_info")
    public JsonResult<User> getInfo(HttpSession session) {
        String username = getUsernameFromSession(session);
        User user = service.findUserByUsername(username);
        System.out.println(user);
        return new JsonResult<>(OK, user);
    }

    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/gif");
    }
    @RequestMapping("/change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileEmptyException("File is empty");
        }
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("File Size is too big");
        }
        String contentType = file.getContentType();
        if (!AVATAR_TYPE.contains(contentType)) {
            throw new FileTypeException("File type is invalid");
        }

        String parent = session.getServletContext().getRealPath("upload");
        System.out.println(parent);
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String originalFileName = file.getOriginalFilename();
        System.out.println("OriginalFileName: "+originalFileName);
        int index = originalFileName.lastIndexOf(".");
        String suffix = originalFileName.substring(index);

        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        System.out.println("Filename: " + filename);
        File dest = new File(dir, filename);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        String avatar = "/upload/"+filename;
        service.changeAvatar(uid, avatar, username);
        return new JsonResult<>(OK, avatar);
    }

}
