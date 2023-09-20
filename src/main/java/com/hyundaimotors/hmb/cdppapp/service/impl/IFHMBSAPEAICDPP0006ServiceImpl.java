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
        
        mapper.transferReplica(map);
        
        /*if(!"Test drive - update".equals(dto.getAction().getType())|| !"Test drive - add".equals(dto.getAction().getType())){
            String actionId = mapper.getTaskRowId(dto);
            resulDto.setActionId(actionId);
        }*/

        
        resulDto.setErrorSpcCode("0");
        resulDto.setErrorSpcMessage("OK");

        return resulDto;
    }
}
