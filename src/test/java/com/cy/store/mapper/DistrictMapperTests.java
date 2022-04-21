package com.cy.store.mapper;

import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {
    @Autowired
    private DistrictMapper mapper;

    @Test
    public void findByParent() {
        List<District> list = mapper.findByParent("120100");
        for (District d : list) {
            System.out.println(d);
        }
    }

    @Test
    public void findNameByCode() {
        System.err.println(mapper.findNameByCode("610000"));
    }
}
