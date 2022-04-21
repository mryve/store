package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.AccessDeniedException;
import com.cy.store.service.ex.AddressMaxCountException;
import com.cy.store.service.ex.AddressNotFoundException;
import com.cy.store.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IAddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper mapper;
    @Autowired
    private DistrictMapper districtMapper;
    @Value("${user.address.max-count}")
    private Integer addressMaxCount;

    @Override
    public void addAddress(Integer uid, String username, Address address) {
        Integer count = mapper.countByUid(uid);
        if (20 < count) {
            throw new AddressMaxCountException("The max number of address is 20");
        }

        String provinceName = districtMapper.findNameByCode(address.getProvinceCode());
        String cityName = districtMapper.findNameByCode(address.getCityCode());
        String areaName = districtMapper.findNameByCode(address.getAreaName());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        address.setUid(uid);
        int isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        address.setModifiedUser(username);
        address.setCreatedUser(username);
        address.setModifiedTime(new Date());
        address.setCreatedTime(new Date());
        mapper.insert(address);
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        return mapper.findByUid(uid);
    }

    @Override
    public void setIsDefault(Integer uid, Integer aid, String username) {
        Address result = mapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("This address is not exist");
        } else if (result.getUid() != uid) {
            throw new AccessDeniedException("Access denied");
        }

        int rows = mapper.updateNoneDefaultByUid(uid);
        if (rows < 1) {
            throw new UpdateException("Unknown update exception occurs");
        }

        Integer isDefault = mapper.updateIsDefaultByAid(aid, username, new Date());
        if (isDefault != 1) {
            throw new UpdateException("Unknown update exception occurs");
        }

    }
}
