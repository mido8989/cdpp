package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0013Mapper;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Adress;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Contact;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0013Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0013ServiceImpl implements IFHMBINNOCEANCDPP0013Service{
    
    private final IFHMBINNOCEANCDPP0013Mapper mapper;   

    public IFHMBINNOCEANCDPP0013Dto upsertObject(IFHMBINNOCEANCDPP0013Dto dto)throws Exception{
        List<IFHMBINNOCEANCDPP0013Adress> addressList = new ArrayList<>();
        List<IFHMBINNOCEANCDPP0013Contact> contactList = new ArrayList<>();
        
        IFHMBINNOCEANCDPP0013Dto resultDto = new IFHMBINNOCEANCDPP0013Dto();
        int checkRowNum = mapper.getRowId(dto);
        

        mapper.insertBusinessAccount(dto);
        mapper.insertAdress(dto);
        mapper.insertPersonAccount(dto);
        mapper.insertType(dto);
        mapper.insertPosition(dto);

        resultDto.setErrorSpcCode("0");
        resultDto.setErrorSpcMessage("OK");
        
        return resultDto;
    }
}
