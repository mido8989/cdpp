package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.Oppertunity.OppertunityDto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBZICARDCDPP0007Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.OppertunityMapper;
import com.hyundaimotors.hmb.cdppapp.service.OppertunityService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OppertunityServiceImpl implements OppertunityService{
    

    private final OppertunityMapper mapper;

    public OppertunityDto insertObject(OppertunityDto dto)throws Exception{
        
        OppertunityDto resultDto = new OppertunityDto();

        if(resultDto.getExternalId() != null){
            mapper.updateObject(dto);
        }else{
            mapper.insertObject(dto);
        }
        resultDto.setErrorSpcCode("0");
        resultDto.setErrorSpcMessage("OK");

        return resultDto;
    }
}
