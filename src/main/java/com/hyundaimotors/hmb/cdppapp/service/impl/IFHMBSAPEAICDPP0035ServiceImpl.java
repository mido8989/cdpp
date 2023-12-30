package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0035Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBSAPEAICDPP0035Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPEAICDPP0035Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBSAPEAICDPP0035ServiceImpl implements IFHMBSAPEAICDPP0035Service{
    
    private final IFHMBSAPEAICDPP0035Mapper mapper;

    public IFHMBSAPEAICDPP0035Dto insertObject(IFHMBSAPEAICDPP0035Dto dto)throws Exception{
        mapper.insertObject(dto);

        HashMap<String, String> map = new HashMap<>();

        map.put("PARAM_ID", String.valueOf(dto.getRowId()));

        mapper.transferProcess(map);

        String attachId = mapper.getAttachId(dto);

        HashMap<String, String> replicaMap = new HashMap<>();

        replicaMap.put("PARAM_ID", String.valueOf(attachId));
        
        mapper.transferReplica(replicaMap);

        IFHMBSAPEAICDPP0035Dto resultDto = new IFHMBSAPEAICDPP0035Dto();
        resultDto.setAttachmentId(attachId);
        resultDto.setErrorSpcCode("0");
        resultDto.setErrorSpcMessage("OK");
        
        return resultDto;
    }    

    public void insertDPObject(IFHMBSAPEAICDPP0035Dto dto)throws Exception{
        HashMap<String, String> map = new HashMap<>();

        map.put("PARAM_ID", dto.getAttachmentId());
        
        mapper.transferDPProcess(map);
    }

}
