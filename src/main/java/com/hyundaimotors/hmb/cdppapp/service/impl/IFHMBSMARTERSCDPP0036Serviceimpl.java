package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSMARTERSCDPP0036Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBSMARTERSCDPP0036Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBZICARDCDPP0007Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSMARTERSCDPP0036Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBSMARTERSCDPP0036Serviceimpl implements IFHMBSMARTERSCDPP0036Service{
    
    private final IFHMBSMARTERSCDPP0036Mapper mapper;

    public IFHMBSMARTERSCDPP0036Dto insertObject(IFHMBSMARTERSCDPP0036Dto dto)throws Exception{
        IFHMBSMARTERSCDPP0036Dto resulDto = new IFHMBSMARTERSCDPP0036Dto();

        mapper.insertServiceRequestChatInput(dto);

        mapper.insertChat(dto);

        mapper.insertMessage(dto);

        HashMap<String, String> map = new HashMap<>();

        map.put("PARAM_ID", String.valueOf(dto.getRowId()));

        mapper.transferProcess(map);
        
        mapper.transferReplica(map);

        resulDto.setErrorSpcCode("0");
        resulDto.setErrorSpcMessage("OK");

        return resulDto;
    }
}
