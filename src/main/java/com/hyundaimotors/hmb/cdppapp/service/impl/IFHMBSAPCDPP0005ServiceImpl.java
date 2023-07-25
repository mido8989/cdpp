package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPCDPP0005Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBSAPCDPP0005Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPCDPP0005Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBSAPCDPP0005ServiceImpl implements IFHMBSAPCDPP0005Service{
    
    private final IFHMBSAPCDPP0005Mapper mapper;

    public IFHMBSAPCDPP0005Dto insertObject(IFHMBSAPCDPP0005Dto dto)throws Exception{

        int resNum01 = mapper.insertSoe(dto);
        int resNum02 = mapper.insertSoex(dto);
        int resNum03 = mapper.insertSapr(dto);

        int totalNum = resNum01 + resNum02 + resNum03;

        IFHMBSAPCDPP0005Dto resulDto = new IFHMBSAPCDPP0005Dto();
        
        if(totalNum == 3){
            resulDto.setContactId(dto.getIntegrationId());
            resulDto.setErrorSpcCode("200");
            resulDto.setErrorSpcMessage("insert success");
        }

        return resulDto;

        
    }

    public IFHMBSAPCDPP0005Dto updateObject(IFHMBSAPCDPP0005Dto dto)throws Exception{
        int resNum01 = mapper.updateSoe(dto);
        //int resNum02 = mapper.updateSoex(dto);
        //int resNum03 = mapper.updateSapr(dto);

        int totalNum = resNum01 /*+ resNum02 + resNum03*/;

        IFHMBSAPCDPP0005Dto resulDto = new IFHMBSAPCDPP0005Dto();

        if(totalNum == /*3*/1){
            resulDto.setContactId(dto.getIntegrationId());
            resulDto.setErrorSpcCode("200");
            resulDto.setErrorSpcMessage("update success");
        }

        return resulDto;
    }

}
