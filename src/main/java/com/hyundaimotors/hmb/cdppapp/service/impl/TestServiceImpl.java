package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hyundaimotors.hmb.cdppapp.dto.AccountDto;
import com.hyundaimotors.hmb.cdppapp.dto.TestDto;
import com.hyundaimotors.hmb.cdppapp.dto.s_contactDto;
import com.hyundaimotors.hmb.cdppapp.mapper.TestMapper;
import com.hyundaimotors.hmb.cdppapp.mapper.impl.CdppMapper;
import com.hyundaimotors.hmb.cdppapp.payload.App;
import com.hyundaimotors.hmb.cdppapp.payload.Car;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowPayLoad;
import com.hyundaimotors.hmb.cdppapp.service.TestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestMapper testMapper;

    private final CdppMapper cdppMapper = new CdppMapper();

    private static final Logger log = LoggerFactory.getLogger(JdbcTemplate.class);

    public TestDto insertList(TestDto dto) throws Exception{
        
        testMapper.insertList(dto);

        return dto;
    }
    
    //@Cacheable(cacheNames = "getList", key = "{#dto.id}")
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

    public InboundContactWorkflowPayLoad.Response upsertInboundContactWorkflow(InboundContactWorkflowPayLoad.Request dto){
        s_contactDto sContactDto = new s_contactDto();
        InboundContactWorkflowPayLoad.Response res = new InboundContactWorkflowPayLoad.Response();
        try{
            cdppMapper.InboundContactWorkflowRequestMap(dto, sContactDto);
            testMapper.InsertInboundContactWorkflow(sContactDto);
            
            res.setContactId(dto.getIntegrationId());
            res.setError_spcCode("0");
            res.setError_spcMessage("OK");
        }catch(Exception e){
            //log.error("Fail upsert :", e);
            res.setContactId(dto.getIntegrationId());
            res.setError_spcCode("-1");
            res.setError_spcMessage(e.getMessage());
        }

        
        
        return res;
    }
}
