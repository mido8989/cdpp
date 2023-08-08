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

        int rowCheckNum = mapper.getRowCheck(dto);
        
        if( rowCheckNum > 0 ){

            mapper.UpdateAutoVehicle(dto);
            mapper.UpdateSAssetXDto(dto);
            mapper.UpdateSAssetAtxDto(dto);
            mapper.UpdateSAssetConDtoList(contact);
            mapper.UpdateSAssetAccntDtoList(account);   

        } else {
            mapper.InsertAutoVehicle(dto);
            mapper.InsertSAssetXDto(dto);
            mapper.InsertSAssetAtxDto(dto);           
            mapper.InsertSAssetConDtoList(contact);
            mapper.UpdateSAssetConDtoList(contact);
            mapper.InsertSAssetAccDtoList(account);
        }

        dto.setErrorSpcCode("0");
        dto.setErrorSpcMessage("OK");

        return dto;
    } 
    
}
