package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0005Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBZICARDCDPP0005Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBZICARDCDPP0005Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IFHMBZICARDCDPP0005ServiceImpl implements IFHMBZICARDCDPP0005Service{
    
    private final IFHMBZICARDCDPP0005Mapper mapper;

    public IFHMBZICARDCDPP0005Dto insertObject(IFHMBZICARDCDPP0005Dto dto)throws Exception{

        int resNum01 = mapper.insertSoe(dto);
        int resNum02 = mapper.insertSoex(dto);
        int resNum03 = mapper.insertSapr(dto);

        int totalNum = resNum01 + resNum02 + resNum03;

        IFHMBZICARDCDPP0005Dto resulDto = new IFHMBZICARDCDPP0005Dto();
        
        if(totalNum == 3){
            resulDto.setContactId(dto.getIntegrationId());
            resulDto.setErrorSpcCode("200");
            resulDto.setErrorSpcMessage("insert success");
        }

        return resulDto;

        
    }

    public IFHMBZICARDCDPP0005Dto updateObject(IFHMBZICARDCDPP0005Dto dto)throws Exception{
        int resNum01 = mapper.updateSoe(dto);
        //int resNum02 = mapper.updateSoex(dto);
        //int resNum03 = mapper.updateSapr(dto);

        int totalNum = resNum01 /*+ resNum02 + resNum03*/;

        IFHMBZICARDCDPP0005Dto resulDto = new IFHMBZICARDCDPP0005Dto();

        if(totalNum == /*3*/1){
            resulDto.setContactId(dto.getIntegrationId());
            resulDto.setErrorSpcCode("200");
            resulDto.setErrorSpcMessage("update success");
        }

        return resulDto;
    }

}
