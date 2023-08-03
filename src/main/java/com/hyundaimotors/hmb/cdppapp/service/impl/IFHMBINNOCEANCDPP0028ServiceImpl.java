package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0028Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0027Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0028Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0028Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0028ServiceImpl implements IFHMBINNOCEANCDPP0028Service{
    
    private final IFHMBINNOCEANCDPP0028Mapper mapper;  

    public IFHMBINNOCEANCDPP0028Dto upsertObject(IFHMBINNOCEANCDPP0028Dto dto)throws Exception{
        IFHMBINNOCEANCDPP0028Dto resulDto = new IFHMBINNOCEANCDPP0028Dto();

        int checkRowNum = 0;

        checkRowNum = mapper.checkRowId(dto);

        if(checkRowNum == 0){
            mapper.insertObject(dto);
        }else{
            mapper.updateObject(dto);
        }
        
        dto.setErrorSpcCode("0");
        dto.setErrorSpcMessage("OK");
        
        return resulDto;
    }
}
