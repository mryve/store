package com.cy.store.service;


import com.cy.store.entity.Address;

import java.util.List;

public interface IAddressService {
   void addAddress(Integer uid, String username, Address address);
   List<Address> getByUid(Integer uid);
   void setIsDefault(Integer uid, Integer aid, String username);
   void delete(Integer aid, Integer uid, String username);
}
