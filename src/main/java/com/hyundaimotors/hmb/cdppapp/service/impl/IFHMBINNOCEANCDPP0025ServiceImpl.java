package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0025Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0025Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0027Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0025Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0025ServiceImpl implements IFHMBINNOCEANCDPP0025Service{
    
    private final IFHMBINNOCEANCDPP0025Mapper mapper;  

    public IFHMBINNOCEANCDPP0025Dto upsertObject(IFHMBINNOCEANCDPP0025Dto dto)throws Exception{

        IFHMBINNOCEANCDPP0025Dto resultDto = new IFHMBINNOCEANCDPP0025Dto();

        int cpfCheck = mapper.getCPFCheck(dto);

        if(0 < cpfCheck){
            int resultNum = mapper.updateObject(dto);
        }else{
            int resultNum = mapper.insertObject(dto);
        }

        resultDto.setErrorSpcCode("0");
        resultDto.setErrorSpcMessage("OK");
        resultDto.setSrNumber(dto.getSrNumber());
        return resultDto;
    }
}
