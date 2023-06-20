package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;

import com.hyundaimotors.hmb.cdppapp.dto.TestDto;
import com.hyundaimotors.hmb.cdppapp.mapper.TestMapper;
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

    public TestDto getList(TestDto dto) throws Exception{

        return testMapper.getList(dto);
    }
}
