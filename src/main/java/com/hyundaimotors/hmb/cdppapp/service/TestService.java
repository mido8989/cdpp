package com.hyundaimotors.hmb.cdppapp.service;

import com.hyundaimotors.hmb.cdppapp.dto.TestDto;

public interface TestService {

    public TestDto insertList(TestDto dto) throws Exception;

    public TestDto getList(TestDto dto) throws Exception;
}
