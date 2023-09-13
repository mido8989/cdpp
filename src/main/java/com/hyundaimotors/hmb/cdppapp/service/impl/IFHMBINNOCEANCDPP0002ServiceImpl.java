package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0002Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0002Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0002ServiceImpl implements IFHMBINNOCEANCDPP0002Service{
    
    private final IFHMBINNOCEANCDPP0002Mapper mapper;

    public IFHMBINNOCEANCDPP0002Dto insertObject(IFHMBINNOCEANCDPP0002Dto dto)throws Exception{
        //To_Do : Return ContactId를 Process Id로 변경, Exception Runtime 으로 변경
    
        IFHMBINNOCEANCDPP0002Dto resultDto = new IFHMBINNOCEANCDPP0002Dto();

        mapper.insertObject(dto);
        mapper.insertCar(dto);
        mapper.insertApp(dto);
        mapper.insertSocialmedia(dto);
        mapper.insertHobby(dto);
        mapper.insertSoccerteam(dto);
        
        HashMap<String, String> map = new HashMap<>();
        map.put("PARAM_ID", String.valueOf(dto.getRowId()));
        
        mapper.transferProcess(map);
        mapper.transferReplica(map);

        String processingRowId = mapper.getContactId(dto);
        
        resultDto.setContactId(processingRowId);
        resultDto.setError_spcCode("0");
        resultDto.setError_spcMessage("OK");      
        
        return resultDto;
    }
}
