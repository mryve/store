package com.cy.store.controller;

import com.cy.store.entity.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {
    @Autowired
    private IAddressService service;

    @RequestMapping("/add")
    public JsonResult<Void> addAddress(HttpSession session, Address address) {
        Integer uid = (Integer) session.getAttribute("uid");
        String username = (String) session.getAttribute("username");
        service.addAddress(uid, username, address);
        return new JsonResult<>(OK);
    }

    @RequestMapping({"/", ""})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> list = service.getByUid(uid);
        return new JsonResult<>(OK,list);
    }
}
