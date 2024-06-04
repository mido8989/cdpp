package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.OpportunityReverse.OpportunityDto;
import com.hyundaimotors.hmb.cdppapp.mapper.OpportunityMapper;
import com.hyundaimotors.hmb.cdppapp.service.OpportunityService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OpportunityServiceImpl implements OpportunityService{
    
    private final OpportunityMapper mapper;

    public OpportunityDto insertObject(OpportunityDto dto)throws Exception{
        
        OpportunityDto resultDto = new OpportunityDto();
        int countRowId = mapper.getRowId(dto);

        if(countRowId == 0){
            mapper.insertObject(dto);
            
        }else{
            //mapper.updateObject(dto);
        }
        resultDto.setRowId(dto.getRowId());
        resultDto.setErrorSpcCode("0");
        resultDto.setErrorSpcMessage("OK");

        return resultDto;
    }
}
