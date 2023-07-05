package com.hyundaimotors.hmb.cdppapp.controller;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.AccountDto;
import com.hyundaimotors.hmb.cdppapp.dto.TestDto;
import com.hyundaimotors.hmb.cdppapp.payload.AccountPayLoad;
import com.hyundaimotors.hmb.cdppapp.service.TestService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private final TestService testService;

    private final ModelMapper defaultMapper;

    @PostMapping(value = "/insert")
    public TestDto insertList(@RequestBody TestDto dto) throws Exception {

        TestDto responDto = testService.insertList(dto);

        return responDto;
    }

    @GetMapping(value = "/list")
    public TestDto getList(@RequestBody TestDto dto) throws Exception {
        TestDto responDto = testService.getList(dto);
        return responDto;
    }
    
    
    @GetMapping("/get/{id}")
    public TestDto getRecord(@PathVariable("id") String recordId) throws Exception {
        TestDto param = new TestDto();
        param.setId(recordId);
        TestDto responDto = testService.getList(param);
        
        return responDto;
    }

    @PostMapping(value = "/Account")
    public Object accountList(@RequestBody AccountPayLoad.Request request)throws Exception{
        

        AccountDto dto = defaultMapper.map(request, AccountDto.class);

        AccountDto resultDto = testService.getAccountList(dto);

        return defaultMapper.map(resultDto, AccountPayLoad.Response.class);
    }
}
