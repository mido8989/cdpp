package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.s_contactDto;
import com.hyundaimotors.hmb.cdppapp.dto.s_contact_xDto;
import com.hyundaimotors.hmb.cdppapp.dto.s_contact_xmDto;
import com.hyundaimotors.hmb.cdppapp.mapper.TestMapper;
import com.hyundaimotors.hmb.cdppapp.mapper.impl.CdppMapper;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowPayLoad;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0002Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0002ServiceImpl implements IFHMBINNOCEANCDPP0002Service{
    
    private final TestMapper testMapper;

    private final CdppMapper cdppMapper = new CdppMapper();


    public InboundContactWorkflowPayLoad.Response insertInboundContactWorkflow(InboundContactWorkflowPayLoad.Request dto){
        s_contactDto sContactDto = new s_contactDto();
        s_contact_xDto sContactXDto = new s_contact_xDto();
        List<s_contact_xmDto> sContactXmDtoList = new ArrayList<s_contact_xmDto>();
        InboundContactWorkflowPayLoad.Response res = new InboundContactWorkflowPayLoad.Response();
        try{
            cdppMapper.InboundContactWorkflowRequestMap(dto, sContactDto, sContactXDto, sContactXmDtoList);
            testMapper.InsertInboundContactWorkflow(sContactDto);
            testMapper.InsertSContactXDto(sContactXDto);
            testMapper.InsertSContactXMDtoList(sContactXmDtoList);
            
            res.setContactId(dto.getIntegrationId());
            res.setError_spcCode("0");
            res.setError_spcMessage("OK");
        }catch(Exception e){
            //log.error("Fail upsert :", e);
            res.setContactId(dto.getIntegrationId());
            res.setError_spcCode("-1");
            res.setError_spcMessage(e.getMessage());
        }

        
        
        return res;
    }
}
