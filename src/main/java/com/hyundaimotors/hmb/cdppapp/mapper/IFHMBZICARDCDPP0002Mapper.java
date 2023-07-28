package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;

import org.mybatis.spring.annotation.MapperScan;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0002Dto;

@MapperScan
public interface IFHMBZICARDCDPP0002Mapper {
    public int insertContact(IFHMBZICARDCDPP0002Dto dto)throws Exception;

    public void transferContact(HashMap<String, String> parMap)throws Exception;
}
