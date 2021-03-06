package com.cy.store.service;

import com.cy.store.entity.District;
import com.cy.store.service.impl.IDistrictServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTests {
    @Autowired
    private IDistrictServiceImpl service;

    @Test
    public void getByParent() {
        List<District> l = service.getByParent("86");
        for (District d : l) {
            System.err.println(d);
        }

    }
}
