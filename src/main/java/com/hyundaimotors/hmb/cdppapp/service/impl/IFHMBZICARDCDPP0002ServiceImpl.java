package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0002Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBZICARDCDPP0002Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBZICARDCDPP0002Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBZICARDCDPP0002ServiceImpl implements IFHMBZICARDCDPP0002Service{
    
    private final IFHMBZICARDCDPP0002Mapper mapper;

    public IFHMBZICARDCDPP0002Dto insertObject(IFHMBZICARDCDPP0002Dto dto)throws Exception{

        IFHMBZICARDCDPP0002Dto resultDto = new IFHMBZICARDCDPP0002Dto();


        int resultNum01 = mapper.insertContact(dto);

        System.out.println("row id is = " + dto.getRow_id());

        if(resultNum01 > 0){
            resultDto.setError_spcCode("200");
            resultDto.setError_spcMessage("success");
        }
        
        HashMap<String, String> parMap = new HashMap<String, String>();
        parMap.put("PARAM_ID", dto.getRow_id());

        mapper.transferAccount(parMap);

        return resultDto;
    }
}
