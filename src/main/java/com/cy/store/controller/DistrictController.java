package com.cy.store.controller;

import com.cy.store.entity.District;
import com.cy.store.service.IDistrictService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/districts")
public class DistrictController extends BaseController{
    @Autowired
    private IDistrictService service;

    @RequestMapping({"", "/"})
    public JsonResult<List<District>> getByParent(String parent) {
        List<District> list = service.getByParent(parent);
        return new JsonResult<>(OK,list);
    }
}
