package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0037Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0037Mapper;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfAutoVehicle;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactHobby;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactSoccerTeam;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactSocialMedia;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.UpdateContactOutput;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.UpdateContactOutputResult;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0037Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0037ServiceImpl implements IFHMBINNOCEANCDPP0037Service{
    
    private final IFHMBINNOCEANCDPP0037Mapper mapper;

    public HashMap<String, Object> updateObject(IFHMBINNOCEANCDPP0037Dto dto)throws Exception{
        
        List<ListOfAutoVehicle> listVehicle = new ArrayList<>();
        List<ListOfContactHobby> listHobby = new ArrayList<>();
        List<ListOfContactSoccerTeam> listSoccerTeam = new ArrayList<>();
        List<ListOfContactSocialMedia> listSocialMedia = new ArrayList<>();
        
        UpdateContactOutputResult updateContactOutputResult = new UpdateContactOutputResult();
        UpdateContactOutput updateContactOutput = new UpdateContactOutput();        
        
        HashMap<String, String> processMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();            
        
        mapper.insertObject(dto);
        mapper.insertAutoVehicle(dto);
        mapper.insertHobby(dto);
        mapper.insertSoccerTeam(dto);
        mapper.insertSocialMedia(dto); 
        
        int checkVehicleInfo = mapper.checkVehicleInfo(dto);

        if( checkVehicleInfo> 0 ){
            processMap.put("ASSET_ID", "Y");
        }

        processMap.put("PARAM_ID", String.valueOf(dto.getRowId()));
        processMap.put("CONTACT_ID", dto.getContactId());
        
        mapper.transferProcess(processMap);

        mapper.getAcountAndContactId(dto);  
        processMap.put("PARAM_ID_01", dto.getProcessContactRowId());
        processMap.put("PARAM_ID_02", dto.getProcessAccountRowId());       
        
        mapper.transferReplica(processMap);
        
        updateContactOutputResult.setContactId(dto.getContactId());
        updateContactOutputResult.setErrorSpcCode("1");
        updateContactOutputResult.setErrorSpcMessage("OK");        
        map.put("updateContactOutputResult", updateContactOutputResult);

        updateContactOutput = mapper.getUpdateContactOutput(dto);
        map.put("updateContactOutput", updateContactOutput);

        listVehicle = mapper.getListVehicle(dto);
        map.put("listOfAutoVehicle", listVehicle);

        listHobby = mapper.getListHobby(dto);
        map.put("listOfContactHobby", listHobby);

        listSoccerTeam = mapper.getListSoccerTeam(dto);
        map.put("listOfContactSoccerTeam", listSoccerTeam);

        listSocialMedia = mapper.getListSocialMedia(dto);
        map.put("listOfContactSocialMedia", listSocialMedia);

        return map;
    }
    
}
