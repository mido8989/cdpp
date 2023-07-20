package com.hyundaimotors.hmb.cdppapp.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0001Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBZICARDCDPP0001Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBZICARDCDPP0001Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IFHMBZICARDCDPP0001ServiceImpl implements IFHMBZICARDCDPP0001Service {

    private final IFHMBZICARDCDPP0001Mapper mapper;

    public IFHMBZICARDCDPP0001Dto getObject(IFHMBZICARDCDPP0001Dto dto)throws Exception{
        
        IFHMBZICARDCDPP0001Dto resulDto = mapper.getObject(dto);
        if(resulDto == null || "".equals(resulDto.getCpf()) || "".equals(resulDto.getContactId()) ){
            resulDto.setErrorSpcCode("500");
            resulDto.setErrorSpcMessage("empty data");

        }else{
            resulDto.setErrorSpcCode("200");
            resulDto.setErrorSpcMessage("success");
        }
        return resulDto;
    }
    
}
