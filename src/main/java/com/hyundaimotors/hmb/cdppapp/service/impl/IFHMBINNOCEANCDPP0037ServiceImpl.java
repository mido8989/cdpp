package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0037.IFHMBINNOCEANCDPP0037Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0037.ListOfAutoVehicleDto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0037Mapper;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfAutoVehicle;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactHobby;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactSoccerTeam;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactSocialMedia;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.UpdateContactOutput;
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
        
        UpdateContactOutput updateContactOutput = new UpdateContactOutput();  
        
        HashMap<String, String[]> processMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        List<ListOfAutoVehicleDto> listOfAutoVehicle = new ArrayList<>();         
        List<String> listVehicleId = new ArrayList<>();
        List<String> listParamId = new ArrayList<>();
        List<String> listAccountId = new ArrayList<>();
        List<String> listAssetId = new ArrayList<>();

        mapper.insertObject(dto);
        mapper.insertHobby(dto);
        mapper.insertSoccerTeam(dto);
        mapper.insertSocialMedia(dto); 

        listOfAutoVehicle = dto.getListOfAutoVehicle();
        listParamId.add(String.valueOf(dto.getRowId()));
        listAccountId.add(dto.getContactId());

        if( listOfAutoVehicle.size() > 0 ){
            for(int i = 0; i < listOfAutoVehicle.size(); i++){
                ListOfAutoVehicleDto vehicle = new ListOfAutoVehicleDto();
                vehicle = listOfAutoVehicle.get(i);
                mapper.insertAutoVehicle(vehicle);
                listVehicleId.add(String.valueOf(vehicle.getRowId()));
                listAssetId.add(String.valueOf(vehicle.getVehicleId()));
            }
        }
        String[] vehicleId = listVehicleId.toArray(new String[listVehicleId.size()]);
        String[] paramId = listParamId.toArray(new String[listParamId.size()]);
        String[] accountId = listAccountId.toArray(new String[listAccountId.size()]);
        String[] assetId = listAssetId.toArray(new String[listAssetId.size()]);


        // landing dummy rowId
        processMap.put("PARAM_ID", paramId);
        // landing dummy vehicle rowId list
        processMap.put("VEHICLE_ID", vehicleId);
        // process account rowId       
        processMap.put("ACCOUNT_ID", accountId);
        // process asset rowId list
        processMap.put("ASSET_ID_LIST", assetId);

        mapper.transferProcess(processMap);    
        
        mapper.transferReplica(processMap);


        map.put("contactId", dto.getContactId());
        map.put("error_spcCode", "0");
        map.put("error_spcMessage", "OK");

        updateContactOutput = mapper.getUpdateContactOutput(dto);  
        updateContactOutput.setContactId(dto.getContactId());      
        map.put("updateContactOutput", updateContactOutput);

        listVehicle = mapper.getListVehicle(processMap);
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
