package com.hyundaimotors.hmb.cdppapp.mapper;

import com.hyundaimotors.hmb.cdppapp.dto.TestDto;


public interface TestMapper {
    public void insertList(TestDto dto) throws Exception;

    public TestDto getList(TestDto dto) throws Exception;
}
