package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.AccountDto;
import com.hyundaimotors.hmb.cdppapp.dto.TestDto;
import com.hyundaimotors.hmb.cdppapp.payload.App;
import com.hyundaimotors.hmb.cdppapp.payload.Car;


public interface TestMapper {
    public void insertList(TestDto dto) throws Exception;

    public TestDto getList(TestDto dto) throws Exception;

    public int getAccountList(AccountDto dto) throws Exception;

    public int getCarList(List<Car> cars)throws Exception;

    public int getAppsList(List<App> apps)throws Exception;
}
