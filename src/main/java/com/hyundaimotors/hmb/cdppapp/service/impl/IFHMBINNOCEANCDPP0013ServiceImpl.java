package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013.ListOfContactsDto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0013Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0013Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0013ServiceImpl implements IFHMBINNOCEANCDPP0013Service{
    
    private final IFHMBINNOCEANCDPP0013Mapper mapper;   

    public IFHMBINNOCEANCDPP0013Dto upsertObject(IFHMBINNOCEANCDPP0013Dto dto)throws Exception{
        List<ListOfContactsDto> listOfcontacts = new ArrayList<>();
        IFHMBINNOCEANCDPP0013Dto resultDto = new IFHMBINNOCEANCDPP0013Dto();
        
        List<String> landingContact = new ArrayList<String>();
        List<String> processContact = new ArrayList<String>();
        List<String> replicaContact = new ArrayList<String>();
        
        listOfcontacts = dto.getContactList();
        
        //landing 
        mapper.insertBusinessAccount(dto);
        String landingAccountRowId = String.valueOf(dto.getRowId());
        String processAccountRowId = mapper.getProcessAccountRowId(dto);
        String replicaAccountId    = mapper.getReplicaAccountId(dto);
        
        HashMap<String, String[]> procParam = new HashMap<String, String[]>();
        
        procParam.put("LANDING_ACCOUNT_ROW_ID", new String[]{landingAccountRowId});
        procParam.put("PROCESS_ACCOUNT_ROW_ID", new String[]{processAccountRowId});
        procParam.put("REPLICA_ACCOUNT_ID", new String[]{replicaAccountId});
        
        if( listOfcontacts != null ){
            for(int i = 0; i < listOfcontacts.size(); i++){
               ListOfContactsDto contact = new ListOfContactsDto();
               contact = listOfcontacts.get(i);
               
               mapper.insertPersonAccount(contact);
               /**
                * 
                */
               String processContactRowId = mapper.getProcessContactRowId(contact);
               String replicaContactId    = mapper.getReplicaContactId(contact);
               
               landingContact.add(String.valueOf(contact.getRowId()));
               processContact.add(processContactRowId);
               replicaContact.add(replicaContactId);
            }
            
            procParam.put("LANDING_CONTACT_ROW_ID",landingContact.toArray(new String[landingContact.size()]));
            procParam.put("PROCESS_CONTACT_ROW_ID",processContact.toArray(new String[processContact.size()]));
            procParam.put("REPLICA_CONTACT_ID"    ,replicaContact.toArray(new String[replicaContact.size()]));
       }
        
        mapper.transferProcess(procParam);
        /** 신규일때 또는 중복 일때 **/
        
        procParam.put("PROCESS_ACCOUNT_ROW_ID", new String[]{mapper.getProcessAccountRowId(dto)});
        procParam.put("REPLICA_ACCOUNT_ID"    , new String[]{mapper.getReplicaAccountId(dto)});
        if( listOfcontacts != null ){
            for(int i = 0; i < listOfcontacts.size(); i++){
               ListOfContactsDto contact = new ListOfContactsDto();
               contact = listOfcontacts.get(i);
               
               String processContactRowId = mapper.getProcessContactRowId(contact);
               String replicaContactId    = mapper.getReplicaContactId(contact);
               
               processContact.add(processContactRowId);
               replicaContact.add(replicaContactId);
            }
            
            procParam.put("PROCESS_CONTACT_ROW_ID",processContact.toArray(new String[processContact.size()]));
            procParam.put("REPLICA_CONTACT_ID"    ,replicaContact.toArray(new String[replicaContact.size()]));
       }
        mapper.transferReplica(procParam);
        
        resultDto.setErrorSpcCode("0");
        resultDto.setErrorSpcMessage("OK");
        
        return resultDto;
    }
}
