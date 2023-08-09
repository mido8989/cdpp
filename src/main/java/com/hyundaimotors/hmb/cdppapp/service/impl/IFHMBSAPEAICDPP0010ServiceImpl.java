package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
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

        System.out.println("IFHMBSAPEAICDPP0010Dto ===== > " + Json.pretty(dto));

        List<AutoVehicleWebserviceWFContactPayload> contact = new ArrayList<>();
        List<AutoVehicleWebserviceWFAccountPayload> account = new ArrayList<>();

        contact = dto.getContact();
        account = dto.getAccount();
        
        List<IFHMBSAPEAICDPP0010Dto> dtoList = mapper.getRowCheck(dto);        
        
        if( dtoList.size() > 0 ){

            dto.setRowId(dtoList.get(0).getRowId());

            mapper.UpdateAutoVehicle(dto);
            System.out.println("=== UpdateAutoVehicle OK ===");
            mapper.UpdateSAssetXDto(dto);
            System.out.println("=== UpdateSAssetXDto OK ===");
            mapper.UpdateSAssetAtxDto(dto);
            System.out.println("=== UpdateSAssetAtxDto OK ===");
            
            if (contact != null && contact.size() > 0) {
                for (AutoVehicleWebserviceWFContactPayload cont : contact) {
                    cont.setAssetRowId(dto.getRowId());
                }       
                mapper.UpdateSAssetConDtoList(contact);     
                System.out.println("=== UpdateSAssetConDtoList OK ===");
            }
            
            if (account != null && account.size() > 0){
                for (AutoVehicleWebserviceWFAccountPayload acct : account) {
                    acct.setAssetRowId(dto.getRowId());
                }       
                mapper.UpdateSAssetAccntDtoList(account);   
                System.out.println("=== UpdateSAssetAccntDtoList OK ===");
            }
            
        } else {
            
            
            mapper.InsertAutoVehicle(dto);
            System.out.println("=== InsertAutoVehicle OK === rowId ==== > " + dto.getRowId());
            mapper.InsertSAssetXDto(dto);
            System.out.println("=== InsertSAssetXDto OK ===");
            mapper.InsertSAssetAtxDto(dto);
            System.out.println("=== InsertSAssetAtxDto OK ===");
            
            System.out.println("contact size ==== > " + contact.size());
            if (contact != null && contact.size() > 0) {                
                for (AutoVehicleWebserviceWFContactPayload cont : contact) {
                    cont.setAssetRowId(dto.getRowId());
                }       
                mapper.InsertSAssetConDtoList(contact);
                System.out.println("=== InsertSAssetConDtoList OK ===");
                mapper.UpdateSConDtoList(contact);
                System.out.println("=== UpdateSConDtoList OK ===");
                
            }
            
            if (account != null && account.size() > 0){
                for (AutoVehicleWebserviceWFAccountPayload acct : account) {
                    acct.setAssetRowId(dto.getRowId());
                }       
                mapper.InsertSAssetAccDtoList(account);
                System.out.println("=== InsertSAssetAccDtoList OK ===");
            }
        }

        
        dto.setErrorSpcCode("0");
        dto.setErrorSpcMessage("OK");

        System.out.println("DONE ! ! !");

        return dto;
    } 
    
}
