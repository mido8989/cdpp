package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0046Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0046Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0046Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0046ServiceImpl implements IFHMBINNOCEANCDPP0046Service{
    
    private final IFHMBINNOCEANCDPP0046Mapper mapper;

    public IFHMBINNOCEANCDPP0046Dto insertList(IFHMBINNOCEANCDPP0046Dto dto)throws Exception{
        String parRowId = mapper.getParRowId(dto);
        dto.setParRowId(parRowId);
        int resDeleteNum = mapper.deleteList(dto);

        int resInsertNum = mapper.insertList(dto);

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("PARAM_ID", dto.getParRowId());
        
        mapper.transferProcess(map);

        dto.setErrorSpcCode("0");
        dto.setErrorSpcMessage("OK");        

        return dto;
    }
}
