package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0037Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0032Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0037Mapper;
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

        List<ListOfContactHobby> listOfContactHobby = new ArrayList<>();
        listOfContactHobby = dto.getListOfContactHobby();
        List<ListOfContactSoccerTeam> listOfContactSoccerTeam = new ArrayList<>();
        listOfContactSoccerTeam = dto.getListOfContactSoccerTeam();
        List<ListOfContactSocialMedia> listOfContactSocialMedia = new ArrayList<>();
        listOfContactSocialMedia = dto.getListOfContactSocialMedia();
        UpdateContactOutputResult updateContactOutputResult = new UpdateContactOutputResult();
        UpdateContactOutput updateContactOutput = new UpdateContactOutput();
        List<ListOfContactHobby> listHobby = new ArrayList<>();
        List<ListOfContactSoccerTeam> listSoccerTeam = new ArrayList<>();
        List<ListOfContactSocialMedia> listSocialMedia = new ArrayList<>();
        HashMap<String, String> processMap = new HashMap<>();

        for(int i =0; i <listOfContactHobby.size(); i++){
            listOfContactHobby.get(i).setHobbyContactId(dto.getContactId());
        }

        for(int j = 0; j < listOfContactSoccerTeam.size(); j++){
            listOfContactSoccerTeam.get(j).setSoccerTeamContactId(dto.getContactId());
        }

        for(int k = 0; k < listOfContactSocialMedia.size(); k++){
            listOfContactSocialMedia.get(k).setSocialMediaContactId(dto.getContactId());
        }

        HashMap<String, Object> map = new HashMap<>();

        int resultNum01 = mapper.updateContact(dto);
        int resultNum02 = mapper.updateContactx(dto);
        int resultNum03 = mapper.updateContactHobby(listOfContactHobby);
        int resultNum04 = mapper.updateSoccerTeam(listOfContactSoccerTeam);
        int resultNum05 = mapper.updateContactSocialMedia(listOfContactSocialMedia);

        
        processMap.put("PARAM_ID", dto.getContactId());
        
        mapper.transferProcess(processMap);

        IFHMBINNOCEANCDPP0037Dto replicaIdDto = mapper.getAcountAndContactId(dto);

        processMap.put("PARAM_ID_01", dto.getReplicaContactRowId());
        processMap.put("PARAM_ID_02", dto.getReplicaAccountRowId());

        mapper.transferReplica(processMap);

        updateContactOutputResult.setContactId(dto.getContactId());
        updateContactOutputResult.setErrorSpcCode("1");
        updateContactOutputResult.setErrorSpcMessage("OK");

        map.put("updateContactOutputResult", updateContactOutputResult);

        updateContactOutput = mapper.getUpdateContactOutput(dto);

        map.put("updateContactOutput", updateContactOutput);

        listHobby = mapper.getListHobby(dto);

        map.put("listOfContactHobby", listHobby);

        listSoccerTeam = mapper.getListSoccerTeam(dto);

        map.put("listOfContactSoccerTeam", listSoccerTeam);

        listSocialMedia = mapper.getListSocialMedia(dto);

        map.put("listOfContactSocialMedia", listSocialMedia);

        return map;
    }
    
}
