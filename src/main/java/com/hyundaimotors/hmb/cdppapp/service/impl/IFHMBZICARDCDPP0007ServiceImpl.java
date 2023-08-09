package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0007Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBZICARDCDPP0007Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBZICARDCDPP0007Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBZICARDCDPP0007ServiceImpl implements IFHMBZICARDCDPP0007Service{

    private final IFHMBZICARDCDPP0007Mapper mapper;

    public int upsertObject(IFHMBZICARDCDPP0007Dto dto)throws Exception{
        int parRowCheckNum = mapper.getParRowCheck(dto);
        int resultNum = 0;
        if(parRowCheckNum > 0 ){
            resultNum = mapper.updateObject(dto);
        }else{
            resultNum = mapper.insertObject(dto);
        }
        return resultNum;
    }
    
}
