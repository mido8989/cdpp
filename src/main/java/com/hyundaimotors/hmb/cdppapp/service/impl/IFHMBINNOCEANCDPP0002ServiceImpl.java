package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002Dto;
import com.hyundaimotors.hmb.cdppapp.dto.s_contact_xmDto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0002Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.impl.CdppMapper;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowAppPayLoad;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowCarPayLoad;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowHobbyPayLoad;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowPayLoad;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowSnsPayLoad;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowSoccerTeamPayLoad;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0002Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0002ServiceImpl implements IFHMBINNOCEANCDPP0002Service{
    
    private final IFHMBINNOCEANCDPP0002Mapper mapper;

    public IFHMBINNOCEANCDPP0002Dto insertObject(IFHMBINNOCEANCDPP0002Dto dto)throws Exception{
        List<InboundContactWorkflowCarPayLoad> car = new ArrayList<>();
        List<InboundContactWorkflowAppPayLoad> app = new ArrayList<>();
        List<InboundContactWorkflowSnsPayLoad> socialMedia = new ArrayList<>();
        List<InboundContactWorkflowHobbyPayLoad> hobby = new ArrayList<>();
        List<InboundContactWorkflowSoccerTeamPayLoad> soccerTeam;

        
        
            car = dto.getCar();
            app = dto.getApp();
            socialMedia = dto.getSocialMedia();
            hobby = dto.getHobby();

            int resNum01 = mapper.InsertInboundContactWorkflow(dto);
            int resNum02 = mapper.InsertSContactXDto(dto);
            for(int i = 0; i <car.size(); i++){
                car.get(i).setParRowId(dto.getRowId());
            }
            
            int resNum03 = mapper.InsertSContactXMDtoList(car);
            
            HashMap<String, String> map = new HashMap<>();
            map.put("PARAM_ID", dto.getRowId());
            
            mapper.transferContact(map);
            dto.setContactId(dto.getRowId());
            dto.setError_spcCode("0");
            dto.setError_spcMessage("OK");


            //System.err.println("error:" + e);
            //dto.setError_spcCode("-1");
            //dto.setError_spcMessage(e.getMessage());
        

        
        
        return dto;
    }
}
