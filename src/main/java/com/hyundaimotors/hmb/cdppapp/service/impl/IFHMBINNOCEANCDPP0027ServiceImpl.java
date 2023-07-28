package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0027Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0022Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0027Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0027Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0027ServiceImpl implements IFHMBINNOCEANCDPP0027Service{
    
    private final IFHMBINNOCEANCDPP0027Mapper mapper;  

    public int upsertObject(IFHMBINNOCEANCDPP0027Dto dto)throws Exception{
        int parRowCheckNum = mapper.getParRowCheck(dto);
        int resultNum = 0;
        if(0 < parRowCheckNum){
            resultNum = mapper.updateObject(dto); 
        }else{
            resultNum = mapper.insertObject(dto);
        }
        return resultNum;
    }
}
