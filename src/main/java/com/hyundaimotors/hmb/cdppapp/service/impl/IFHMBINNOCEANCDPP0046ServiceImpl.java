package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.HashMap;

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
        IFHMBINNOCEANCDPP0046Dto resultDto = new IFHMBINNOCEANCDPP0046Dto();
        String parRowId = mapper.getAccRowIdByDealercode(dto);

        if ( parRowId != null ){

            dto.setParRowId(parRowId);
            
            mapper.insertObject(dto);
            mapper.deleteList(dto);
            mapper.insertList(dto);
    
            HashMap<String, String> map = new HashMap<String, String>();
    
            map.put("PARROW_ID", dto.getParRowId());
            
            mapper.transferProcess(map);
            mapper.transferReplica(map);
            
            resultDto.setDealerCode(parRowId);
            resultDto.setErrorSpcCode("0");
            resultDto.setErrorSpcMessage("OK");      

        }else{
            resultDto.setDealerCode(dto.getDealerCode());
            resultDto.setErrorSpcCode("1");
            resultDto.setErrorSpcMessage("FAIL");        
        }

        return resultDto;
    }
}