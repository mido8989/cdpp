package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hyundaimotors.hmb.cdppapp.dto.AccountDto;
import com.hyundaimotors.hmb.cdppapp.dto.TestDto;
import com.hyundaimotors.hmb.cdppapp.mapper.TestMapper;
import com.hyundaimotors.hmb.cdppapp.payload.App;
import com.hyundaimotors.hmb.cdppapp.payload.Car;
import com.hyundaimotors.hmb.cdppapp.service.TestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;

    public TestDto insertList(TestDto dto) throws Exception{
        
        testMapper.insertList(dto);

        return dto;
    }
    
    @Cacheable(cacheNames = "getList", key = "{#dto.id}")
    public TestDto getList(TestDto dto) throws Exception{

        return testMapper.getList(dto);
    }

    public AccountDto getAccountList(AccountDto dto) throws Exception{
        int accountRes = 0;
        int carRes  = 0;
        int appRes  = 0;
        int totalRes  = 0;
        
        List<Car> cars = dto.getCar();
        List<App> apps = dto.getApp();

        accountRes = testMapper.getAccountList(dto);
        carRes = testMapper.getCarList(cars);
        appRes = testMapper.getAppsList(apps);
        
        totalRes = accountRes + carRes + appRes;

        dto.setContactId(dto.getIntegrationId());
        dto.setState(String.valueOf(HttpStatus.CREATED));
        dto.setErrorSpcMessage(String.valueOf(HttpStatus.OK));
        
        
        return dto;
    }
}
