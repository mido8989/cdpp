package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBCSUCDPP0085.IFHMBCSUCDPP0085Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBCSUCDPP0085Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBCSUCDPP0086Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBCSUCDPP0086Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBCSUCDPP0086ServiceImpl implements IFHMBCSUCDPP0086Service{
    private final IFHMBCSUCDPP0086Mapper mapper;

    public IFHMBCSUCDPP0085Dto insertObject(IFHMBCSUCDPP0085Dto dto)throws Exception{
        IFHMBCSUCDPP0085Dto resulDto = new IFHMBCSUCDPP0085Dto();
        resulDto = mapper.getAccountObject(dto);

        if(resulDto != null){
            resulDto.setErrorSpcCode("0");
            resulDto.setErrorSpcMessage("OK");
        }

        return resulDto;
    }
}
