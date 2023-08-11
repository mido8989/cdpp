package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0010Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBSAPEAICDPP0010Mapper;
import com.hyundaimotors.hmb.cdppapp.payload.AutoVehicleWebserviceWFAccountPayload;
import com.hyundaimotors.hmb.cdppapp.payload.AutoVehicleWebserviceWFContactPayload;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPEAICDPP0010Service;

import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBSAPEAICDPP0010ServiceImpl implements IFHMBSAPEAICDPP0010Service {

    private final IFHMBSAPEAICDPP0010Mapper mapper;
    
    public IFHMBSAPEAICDPP0010Dto UpsertAutoVehicle(IFHMBSAPEAICDPP0010Dto dto)throws Exception{

        List<AutoVehicleWebserviceWFContactPayload> contact = new ArrayList<>();
        List<AutoVehicleWebserviceWFAccountPayload> account = new ArrayList<>();

        contact = dto.getContact();
        account = dto.getAccount();
        
        List<IFHMBSAPEAICDPP0010Dto> dtoList = mapper.getRowCheck(dto);        
        
        if( dtoList.size() > 0 ){

            dto.setRowId(dtoList.get(0).getRowId());

            mapper.UpdateAutoVehicle(dto);
            mapper.UpdateSAssetXDto(dto);
            mapper.UpdateSAssetAtxDto(dto);
            
            if (contact != null && contact.size() > 0) {
                for (AutoVehicleWebserviceWFContactPayload cont : contact) {
                    cont.setAssetRowId(dto.getRowId());
                }       
                mapper.UpdateSAssetConDtoList(contact);     
            }
            
            if (account != null && account.size() > 0){
                for (AutoVehicleWebserviceWFAccountPayload acct : account) {
                    acct.setAssetRowId(dto.getRowId());
                }       
                mapper.UpdateSAssetAccntDtoList(account);   
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("PARAM_ID", dto.getRowId());
            map.put("checkcu", "update");

            mapper.transferProcess(map);
            mapper.transferReplica(map);
        } else {
            
            
            mapper.InsertAutoVehicle(dto);
            mapper.InsertSAssetXDto(dto);
            mapper.InsertSAssetAtxDto(dto);
            
            if (contact != null && contact.size() > 0) {                
                for (AutoVehicleWebserviceWFContactPayload cont : contact) {
                    cont.setAssetRowId(dto.getRowId());
                }       
                mapper.InsertSAssetConDtoList(contact);
                mapper.UpdateSConDtoList(contact);
                
            }
            
            if (account != null && account.size() > 0){
                for (AutoVehicleWebserviceWFAccountPayload acct : account) {
                    acct.setAssetRowId(dto.getRowId());
                }       
                mapper.InsertSAssetAccDtoList(account);
            }
            HashMap<String, String> map = new HashMap<>();
            map.put("PARAM_ID", dto.getRowId());
            map.put("checkcu", "insert");

            mapper.transferProcess(map);
            mapper.transferReplica(map);
        }

        
        dto.setErrorSpcCode("0");
        dto.setErrorSpcMessage("OK");

        System.out.println("DONE ! ! !");

        return dto;
    } 
    
}
