package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    AddressMapper mapper;

    @Test
    public void insertTest(){
        Address address = new Address();
        address.setUid(2);
        address.setCreatedTime(new Date());
        address.setCreatedUser("Admin");
        address.setModifiedTime(new Date());
        address.setModifiedUser("Admin");
        System.out.println(mapper.insert(address));
    }

    @Test
    public void countTest() {
        System.out.println(mapper.countByUid(14));
    }

    @Test
    public void findByUid() {
        List<Address> list = mapper.findByUid(14);
        for (Address address : list) {
            System.err.println(address);
        }
    }

    @Test
    public void findAddressByAid() {
        System.err.println(mapper.findByAid(4));
    }
    @Test
    public void updateNoneDefault() {
        System.err.println(mapper.updateNoneDefaultByUid(14));
    }
    @Test
    public void updateIsDefault() {
        System.err.println(mapper.updateIsDefaultByAid(4,"admin", new Date()));
    }
}
