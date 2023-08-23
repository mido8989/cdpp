package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0023Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0024Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0023Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0024Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0023Service;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0024Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0024ServiceImpl implements IFHMBINNOCEANCDPP0024Service{
    private final IFHMBINNOCEANCDPP0024Mapper mapper;   

    public IFHMBINNOCEANCDPP0024Dto insertObject(IFHMBINNOCEANCDPP0024Dto dto)throws Exception{
        
        mapper.insertObject(dto);

        IFHMBINNOCEANCDPP0024Dto resulDto = new IFHMBINNOCEANCDPP0024Dto();
        resulDto.setErrorSpcCode("0");
        resulDto.setErrorSpcMessage("OK");
        return resulDto;
    }

}
