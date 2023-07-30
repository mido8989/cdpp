package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.AccountDto;
import com.hyundaimotors.hmb.cdppapp.dto.TestDto;
import com.hyundaimotors.hmb.cdppapp.dto.s_contactDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002Car;
import com.hyundaimotors.hmb.cdppapp.dto.s_contact_xmDto;
import com.hyundaimotors.hmb.cdppapp.payload.App;
import com.hyundaimotors.hmb.cdppapp.payload.Car;


public interface TestMapper {
    public void insertList(TestDto dto) throws Exception;

    public TestDto getList(TestDto dto) throws Exception;

    public int getAccountList(AccountDto dto) throws Exception;

    public int getCarList(List<Car> cars)throws Exception;

    public int getAppsList(List<App> apps)throws Exception;

    public int InsertInboundContactWorkflow(s_contactDto sContactDto) throws Exception;

    public int InsertSContactXDto(IFHMBINNOCEANCDPP0002Car sContactXDto) throws Exception;

    public int InsertSContactXMDtoList(List<s_contact_xmDto> sContactXmDtoList) throws Exception;
}
