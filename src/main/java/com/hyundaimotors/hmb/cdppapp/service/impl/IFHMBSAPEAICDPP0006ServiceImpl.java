package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0006Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBSAPEAICDPP0003Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBSAPEAICDPP0006Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPEAICDPP0006Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBSAPEAICDPP0006ServiceImpl implements IFHMBSAPEAICDPP0006Service{
    
    private final IFHMBSAPEAICDPP0006Mapper mapper;

    public IFHMBSAPEAICDPP0006Dto insertObject(IFHMBSAPEAICDPP0006Dto dto)throws Exception{
        IFHMBSAPEAICDPP0006Dto resulDto = new IFHMBSAPEAICDPP0006Dto();

        mapper.insertAction(dto);

        mapper.insertActionNote(dto);

        mapper.insertActionProducts(dto);

        mapper.insertSynergyActionAudit(dto);

        HashMap<String, String> map = new HashMap<>();

        map.put("PARAM_ID", String.valueOf(dto.getRowId()));

        mapper.transferProcess(map);

        String actionRowId = mapper.getActionRowId(dto);

        HashMap<String, String> replicaMap = new HashMap<>();

        replicaMap.put("PARAM_ID", actionRowId);
        
        mapper.transferReplica(replicaMap);

        
        resulDto.setErrorSpcCode("0");
        resulDto.setErrorSpcMessage("OK");

        return resulDto;
    }
}
