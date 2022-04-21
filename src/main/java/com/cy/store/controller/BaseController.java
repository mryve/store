package com.cy.store.controller;

import com.cy.store.controller.ex.FileEmptyException;
import com.cy.store.controller.ex.FileIOException;
import com.cy.store.controller.ex.FileSizeException;
import com.cy.store.controller.ex.FileTypeException;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    public static final int OK = 200;
    @ExceptionHandler({ServiceException.class, UpdateException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult result = new JsonResult();
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("This user is already exist");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("Unknown register exception occurs");
        }else if (e instanceof PasswordException) {
            result.setState(6000);
            result.setMessage("Password is incorrect");
        }else if (e instanceof UserNotFoundException) {
            result.setState(6001);
            result.setMessage("This user is not exist");
        } else if (e instanceof FileEmptyException) {
            result.setState(8001);
            result.setMessage("File is empty");
        } else if (e instanceof FileSizeException) {
            result.setState(8002);
            result.setMessage("File size is too big");
        } else if (e instanceof FileIOException) {
            result.setState(8003);
            result.setMessage("File uploading failed");
        } else if (e instanceof FileTypeException) {
            result.setState(8004);
            result.setMessage("File type is incorrect");
        } else if (e instanceof AddressMaxCountException) {
            result.setState(9001);
            result.setMessage("Over max address num");
        }
        return result;
    }

    protected final int getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
