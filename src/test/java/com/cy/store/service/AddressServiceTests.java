package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.service.impl.IUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService service;

    @Test
    public void addAddressTest() {
        Address address = new Address();
        address.setUid(14);
        service.addAddress(14, "Admin", address);
    }

    @Test
    public void setIsDefault() {
        service.setIsDefault(14,4, "admin");
    }

    @Test
    public void delete() {
        service.delete(5, 14, "admin");
    }

}
