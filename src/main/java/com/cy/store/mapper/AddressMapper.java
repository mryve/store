package com.cy.store.mapper;

import com.cy.store.entity.Address;

import java.util.Date;
import java.util.List;

public interface AddressMapper {
    int insert(Address address);

    int countByUid(Integer uid);

    List<Address> findByUid(Integer uid);

    Address findByAid(Integer aid);
    Integer updateNoneDefaultByUid(Integer uid);
    Integer updateIsDefaultByAid(Integer aid, String modifiedUser, Date modifiedTime);
}
