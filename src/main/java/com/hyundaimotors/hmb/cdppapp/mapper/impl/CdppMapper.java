package com.hyundaimotors.hmb.cdppapp.mapper.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.hyundaimotors.hmb.cdppapp.dto.s_contactDto;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowPayLoad;

public class CdppMapper {
    public int InboundContactWorkflowRequestMap(InboundContactWorkflowPayLoad.Request req, s_contactDto sContactDto){
        sContactDto.setIntegration_id(req.getIntegrationId());
        sContactDto.setX_cpf(req.getCPF());
        sContactDto.setFst_name(req.getFirstName());
        sContactDto.setLast_name(req.getLastName());  
        
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatedNow = now.format(formatter);
        sContactDto.setCreated(formatedNow);
        sContactDto.setLast_upd(formatedNow);
        sContactDto.setCreated_by("admin_user");
        sContactDto.setLast_upd_by("admin_user");

        Random random = new Random();
        Long randomNumber = random.nextLong();
        String rowId = String.valueOf(randomNumber);
        if(rowId.length()>15){
            rowId = rowId.substring(0, 15);
        }
        sContactDto.setRow_id(rowId);
        

        return 0;
    }
}
