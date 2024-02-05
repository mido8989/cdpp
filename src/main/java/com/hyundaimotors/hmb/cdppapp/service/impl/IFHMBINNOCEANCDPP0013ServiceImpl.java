package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013.InboundChannelPartnerAuditDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013.ListOfContactsDto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0013Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0013Service;

import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0013ServiceImpl implements IFHMBINNOCEANCDPP0013Service{
    
    private final IFHMBINNOCEANCDPP0013Mapper mapper;   

    public HashMap<String, IFHMBINNOCEANCDPP0013Dto> upsertObject(IFHMBINNOCEANCDPP0013Dto dto)throws Exception{
        List<ListOfContactsDto> listOfcontacts = new ArrayList<>();        
        IFHMBINNOCEANCDPP0013Dto resultDto = new IFHMBINNOCEANCDPP0013Dto();
        List<String> listParamId = new ArrayList<>();
        List<String> listProcAccntId = new ArrayList<>();
        List<String> listConRowId = new ArrayList<>();
        List<String> listProcConId = new ArrayList<>();
        
        // cnpj 숫자만 추출
        if(dto.getCnpj() != null && !dto.getCnpj().equals("")){
            dto.setCnpj(dto.getCnpj().replaceAll("[^0-9]", ""));
        }

        // dealercode 숫자만 들어올 경우 'B05AM' 붙여주기
        if(dto.getDealerCode() != null && !dto.getDealerCode().equals("")){
            if(dto.getDealerCode().length() <= 5 ){
                dto.setDealerCode("B05AM" + dto.getDealerCode());
            }
        }

        // mobile 번호 형식 변경
        // String dtoMainPhone = dto.getMainPhone();
        // if( (dtoMainPhone != null && !dtoMainPhone.equals("")) && (dtoMainPhone.length() == 9 || dtoMainPhone.length() == 11)){
        //     dtoMainPhone = dtoMainPhone.replaceAll("[^0-9]", "");           
        //     if(dtoMainPhone.length() == 9){     // 9자리일 경우
        //         dtoMainPhone = "+55 00 " + dtoMainPhone.substring(0, 5) + " " + dtoMainPhone.substring(5, 9);
        //     }else{      // 11자리일 경우
        //         dtoMainPhone = "+55 " + dtoMainPhone.substring(0, 2) + " " + dtoMainPhone.substring(2, 7) + " " + dtoMainPhone.substring(7, 11);
        //     }
        //     dto.setMainPhone(dtoMainPhone);
        // }

        String getProcAccntRowId = mapper.getRowId(dto);

        mapper.insertBusinessAccount(dto);

        IFHMBINNOCEANCDPP0013Dto oldAccount = new IFHMBINNOCEANCDPP0013Dto();

        oldAccount = mapper.getOldAccount(getProcAccntRowId);
 

        listParamId.add(String.valueOf(dto.getRowId()));
        if(getProcAccntRowId != null){
            listProcAccntId.add(getProcAccntRowId);
        }
        listOfcontacts = dto.getContactList();        
        
        
        if( getProcAccntRowId != null ){ // 딜러 어카운트 존재할 때

            String[] conRowId = null;
            if( listOfcontacts != null ){
                for(int i = 0; i < listOfcontacts.size(); i++){
                    ListOfContactsDto contact = new ListOfContactsDto();
                    contact = listOfcontacts.get(i);
                    contact.setParRowId(String.valueOf(dto.getRowId()));
                    if( contact.getCpf() != null && !contact.getCpf().equals("")){
                        contact.setCpf(contact.getCpf().replaceAll("[^0-9]", ""));
                    }
                    contact.setAccntRowId(getProcAccntRowId);
    
                    mapper.insertPersonAccount(contact);
    
                    listConRowId.add(contact.getRowId());
                }
                conRowId = listConRowId.toArray(new String[listConRowId.size()]);
            }
            String[] paramId = listParamId.toArray(new String[listParamId.size()]);
            String[] procAccntId = listProcAccntId.toArray(new String[listProcAccntId.size()]);

            HashMap<String, String[]> map = new HashMap<>();

            map.put("PARAM_ID", paramId);
            map.put("PROC_ACC_ID", procAccntId);
            map.put("CON_ROWID", conRowId);

            List<String> listCheckcu = new ArrayList<>();
            listCheckcu.add("update");
            String[] checkcu = listCheckcu.toArray(new String[listCheckcu.size()]);
            map.put("checkcu", checkcu); //update
            oldAccount.setCheckUpsert("update");

            mapper.transferProcess(map);

            // process.contact row_id 리스트
            String[] procConId = null;
            if( listOfcontacts != null ){
                for(int i = 0; i < listOfcontacts.size(); i++){
                    ListOfContactsDto contact = new ListOfContactsDto();
                    contact = listOfcontacts.get(i);
                    String getContactId = null;
                    if( contact.getCpf() != null && !contact.getCpf().equals("")){
                        contact.setCpf(contact.getCpf().replaceAll("[^0-9]", ""));
                        getContactId = mapper.getContactId(contact);                        
                        listProcConId.add(getContactId); 
                    }else {
                        getContactId = mapper.getContactIdWithoutCpf(contact);
                        listProcConId.add(getContactId); 
                    }
                    listProcConId.add(getContactId);                      
                    
                }
                procConId = listProcConId.toArray(new String[listProcConId.size()]);
                System.out.println(":::::::: 검색해서 나온 listProcConId :::::::: "+ Json.pretty(listProcConId));
                System.out.println(":::::::: 검색해서 나온 procConId :::::::: "+ Json.pretty(procConId));
            }


            map.put("PROC_CON_ID",procConId);

            mapper.transferReplica(map);

            resultDto.setProcRowId(getProcAccntRowId);
            resultDto.setListOfProcContactRowId(listProcConId);
            resultDto.setErrorSpcCode("0");
            resultDto.setErrorSpcMessage("OK");

        }else{
        
            HashMap<String, String[]> map = new HashMap<>();
            String[] paramId = listParamId.toArray(new String[listParamId.size()]);
            map.put("PARAM_ID", paramId);

            List<String> listCheckcu = new ArrayList<>();
            listCheckcu.add("insert");
            String[] checkcu = listCheckcu.toArray(new String[listCheckcu.size()]);
            map.put("checkcu", checkcu); //insert

            String[] conRowId = null;
            if( listOfcontacts != null ){
                for(int i = 0; i < listOfcontacts.size(); i++){
                    ListOfContactsDto contact = new ListOfContactsDto();
                    contact = listOfcontacts.get(i);
                    contact.setParRowId(String.valueOf(dto.getRowId()));
                    
                    mapper.insertPersonAccount(contact);

                    listConRowId.add(contact.getRowId());
                }
                conRowId = listConRowId.toArray(new String[listConRowId.size()]);
            }   

            map.put("CON_ROWID", conRowId);

            mapper.transferProcess(map);

            getProcAccntRowId = mapper.getRowId(dto);
            String[] procAccntId = null;
            if( getProcAccntRowId != null){
                listProcAccntId.add(getProcAccntRowId);
                procAccntId = listProcAccntId.toArray(new String[listProcAccntId.size()]);
            }
            map.put("PROC_ACC_ID", procAccntId);

            String[] procConId = null;
            // process.contact row_id 리스트
            if( listOfcontacts != null ){
                for(int i = 0; i < listOfcontacts.size(); i++){
                    ListOfContactsDto contact = new ListOfContactsDto();
                    contact = listOfcontacts.get(i);
                    String getContactId = null;
                    if( contact.getCpf() != null && !contact.getCpf().equals("")){
                        contact.setCpf(contact.getCpf().replaceAll("[^0-9]", ""));
                        getContactId = mapper.getContactId(contact);                        
                        listProcConId.add(getContactId); 
                    }else {
                        getContactId = mapper.getContactIdWithoutCpf(contact);
                        listProcConId.add(getContactId); 
                    }
                    listProcConId.add(getContactId);                      
                    
                }
                procConId = listProcConId.toArray(new String[listProcConId.size()]);
            }
     
            map.put("PROC_CON_ID",procConId);

            mapper.transferReplica(map);

            resultDto.setProcRowId(getProcAccntRowId);
            resultDto.setListOfProcContactRowId(listProcConId);
            resultDto.setErrorSpcCode("0");
            resultDto.setErrorSpcMessage("OK");
        }        
        
        HashMap<String, IFHMBINNOCEANCDPP0013Dto> resultMap = new HashMap<>();

        resultMap.put("resultDto", resultDto);
        resultMap.put("oldAccount", oldAccount);

        return resultMap;
    }

    public void insertDPObject(IFHMBINNOCEANCDPP0013Dto dto)throws Exception{

        HashMap<String, String[]> map = new HashMap<>();
        List<String> listProcAccntId = new ArrayList<>();
        List<String> listProcConId = new ArrayList<>();
        String[] procAccntId = null;
        String[] procContId = null;

        listProcAccntId.add(dto.getProcRowId());
        listProcConId = dto.getListOfProcContactRowId();
        System.out.println(":::::: listProcConId :::::: " + Json.pretty(listProcConId));

        procAccntId = listProcAccntId.toArray(new String[listProcAccntId.size()]);
        
        if( !listProcConId.isEmpty() ){
            procContId = listProcConId.toArray(new String[listProcConId.size()]);
        }

        map.put("PROC_ACC_ID", procAccntId);
        map.put("PROC_CON_ID_LIST", procContId);

        mapper.transferDPProcess(map);

        IFHMBINNOCEANCDPP0013Dto newAccount = new IFHMBINNOCEANCDPP0013Dto();

        newAccount = mapper.getOldAccount(dto.getProcRowId());

        List<InboundChannelPartnerAuditDto> auditList = new ArrayList<>();

        if("update".equals(dto.getCheckUpsert())){
            if(dto.getIntegrationId() != null && newAccount.getIntegrationId() != null){
                if(!dto.getIntegrationId().equals(newAccount.getIntegrationId())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("integrationid");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getIntegrationId());
                    auditDto.setNewValue(newAccount.getIntegrationId());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }
            
            if(dto.getName() != null && newAccount.getName() != null){
                if(!dto.getName().equals(newAccount.getName())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("corporateregistrationnumber__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getName());
                    auditDto.setNewValue(newAccount.getName());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getIntegrationId() != null && newAccount.getIntegrationId() != null){
                if(!dto.getIntegrationId().equals(newAccount.getIntegrationId())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("name");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getIntegrationId());
                    auditDto.setNewValue(newAccount.getIntegrationId());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getNickname() != null && newAccount.getNickname() != null){
                if(!dto.getNickname().equals(newAccount.getNickname())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("corporaterepresentativename__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getNickname());
                    auditDto.setNewValue(newAccount.getNickname());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getAccountStatus() != null && newAccount.getAccountStatus() != null){
                if(!dto.getAccountStatus().equals(newAccount.getAccountStatus())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("cust_stat_cd__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getAccountStatus());
                    auditDto.setNewValue(newAccount.getAccountStatus());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getReasonStatus() != null && newAccount.getReasonStatus() != null){
                if(!dto.getReasonStatus().equals(newAccount.getReasonStatus())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("situationstatusreason__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getReasonStatus());
                    auditDto.setNewValue(newAccount.getReasonStatus());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getDealerType() != null && newAccount.getDealerType() != null){
                if(!dto.getDealerType().equals(newAccount.getDealerType())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("dealershoptype__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getDealerType());
                    auditDto.setNewValue(newAccount.getDealerType());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getDealerCode() != null && newAccount.getDealerCode() != null){
                if(!dto.getDealerCode().equals(newAccount.getDealerCode())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("dealercode__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getDealerCode());
                    auditDto.setNewValue(newAccount.getDealerCode());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getMainPhone() != null && newAccount.getMainPhone() != null){
                if(!dto.getMainPhone().equals(newAccount.getMainPhone())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("phone");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getMainPhone());
                    auditDto.setNewValue(newAccount.getMainPhone());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getFax() != null && newAccount.getFax() != null){
                if(!dto.getFax().equals(newAccount.getFax())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("fax");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getFax());
                    auditDto.setNewValue(newAccount.getFax());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getMainEmailAddress() != null && newAccount.getMainEmailAddress() != null){
                if(!dto.getMainEmailAddress().equals(newAccount.getMainEmailAddress())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("emailaddress__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getMainEmailAddress());
                    auditDto.setNewValue(newAccount.getMainEmailAddress());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getWorkEmailAddress() != null && newAccount.getWorkEmailAddress() != null){
                if(!dto.getWorkEmailAddress().equals(newAccount.getWorkEmailAddress())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("sapworkaddress__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getWorkEmailAddress());
                    auditDto.setNewValue(newAccount.getWorkEmailAddress());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSalesDealerFlag() != null && newAccount.getSalesDealerFlag() != null){
                if(!dto.getSalesDealerFlag().equals(newAccount.getSalesDealerFlag())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("salesflag__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSalesDealerFlag());
                    auditDto.setNewValue(newAccount.getSalesDealerFlag());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getServiceDealerFlag() != null && newAccount.getServiceDealerFlag() != null){
                if(!dto.getServiceDealerFlag().equals(newAccount.getServiceDealerFlag())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("serviceflag__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getServiceDealerFlag());
                    auditDto.setNewValue(newAccount.getServiceDealerFlag());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getPartnerStartDate() != null && newAccount.getPartnerStartDate() != null){
                if(!dto.getPartnerStartDate().equals(newAccount.getPartnerStartDate())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("prtnrshp_start_dt__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getPartnerStartDate());
                    auditDto.setNewValue(newAccount.getPartnerStartDate());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getPartnerEndDate() != null && newAccount.getPartnerEndDate() != null){
                if(!dto.getPartnerEndDate().equals(newAccount.getPartnerEndDate())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("prtnrshp_end_dt__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getPartnerEndDate());
                    auditDto.setNewValue(newAccount.getPartnerEndDate());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getRegion() != null && newAccount.getRegion() != null){
                if(!dto.getRegion().equals(newAccount.getRegion())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("region__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getRegion());
                    auditDto.setNewValue(newAccount.getRegion());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getWorkingHours() != null && newAccount.getWorkingHours() != null){
                if(!dto.getWorkingHours().equals(newAccount.getWorkingHours())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("businesshours__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getWorkingHours());
                    auditDto.setNewValue(newAccount.getWorkingHours());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getFacebookPage() != null && newAccount.getFacebookPage() != null){
                if(!dto.getFacebookPage().equals(newAccount.getFacebookPage())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("facebook__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getFacebookPage());
                    auditDto.setNewValue(newAccount.getFacebookPage());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getHomepage() != null && newAccount.getHomepage() != null){
                if(!dto.getHomepage().equals(newAccount.getHomepage())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("website");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getHomepage());
                    auditDto.setNewValue(newAccount.getHomepage());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getStreetAddress() != null && newAccount.getStreetAddress() != null){
                if(!dto.getStreetAddress().equals(newAccount.getStreetAddress())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("billingstreet");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getStreetAddress());
                    auditDto.setNewValue(newAccount.getStreetAddress());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getStreetAddress2() != null && newAccount.getStreetAddress2() != null){
                if(!dto.getStreetAddress2().equals(newAccount.getStreetAddress2())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("billingstreet_2__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getStreetAddress2());
                    auditDto.setNewValue(newAccount.getStreetAddress2());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getStreetAddress3() != null && newAccount.getStreetAddress3() != null){
                if(!dto.getStreetAddress3().equals(newAccount.getStreetAddress3())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("billingstreet_3__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getStreetAddress3());
                    auditDto.setNewValue(newAccount.getStreetAddress3());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getCounty() != null && newAccount.getCounty() != null){
                if(!dto.getCounty().equals(newAccount.getCounty())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("billingcounty");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getCounty());
                    auditDto.setNewValue(newAccount.getCounty());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getCity() != null && newAccount.getCity() != null){
                if(!dto.getCity().equals(newAccount.getCity())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("billingcity");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getCity());
                    auditDto.setNewValue(newAccount.getCity());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getState() != null && newAccount.getState() != null){
                if(!dto.getState().equals(newAccount.getState())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("billingstate");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getState());
                    auditDto.setNewValue(newAccount.getState());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getLatitude() != null && newAccount.getLatitude() != null){
                if(!dto.getLatitude().equals(newAccount.getLatitude())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("billinglatitude");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(String.valueOf(dto.getLatitude()));
                    auditDto.setNewValue(String.valueOf(newAccount.getLatitude()));
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getLongitude() != null && newAccount.getLongitude() != null){
                if(!dto.getLongitude().equals(newAccount.getLongitude())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("billinglongitude");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(String.valueOf(dto.getLongitude()));
                    auditDto.setNewValue(String.valueOf(newAccount.getLongitude()));
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getPostalCode() != null && newAccount.getPostalCode() != null){
                if(!dto.getPostalCode().equals(newAccount.getPostalCode())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("billingpostalcode");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getPostalCode());
                    auditDto.setNewValue(newAccount.getPostalCode());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getDealerGroup() != null && newAccount.getDealerGroup() != null){
                if(!dto.getDealerGroup().equals(newAccount.getDealerGroup())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("dealergroupcode__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getDealerGroup());
                    auditDto.setNewValue(newAccount.getDealerGroup());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSalesGroup() != null && newAccount.getSalesGroup() != null){
                if(!dto.getSalesGroup().equals(newAccount.getSalesGroup())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("salesgroup__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSalesGroup());
                    auditDto.setNewValue(newAccount.getSalesGroup());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSalesManager() != null && newAccount.getSalesManager() != null){
                if(!dto.getSalesManager().equals(newAccount.getSalesManager())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("salesmanager__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSalesManager());
                    auditDto.setNewValue(newAccount.getSalesManager());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getServiceManager() != null && newAccount.getServiceManager() != null){
                if(!dto.getServiceManager().equals(newAccount.getServiceManager())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("servicemanager__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getServiceManager());
                    auditDto.setNewValue(newAccount.getServiceManager());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getServiceWhatsAppNumber() != null && newAccount.getServiceWhatsAppNumber() != null){
                if(!dto.getServiceWhatsAppNumber().equals(newAccount.getServiceWhatsAppNumber())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("servicewhatsappnumber__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getServiceWhatsAppNumber());
                    auditDto.setNewValue(newAccount.getServiceWhatsAppNumber());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSalesWhatsAppNumber() != null && newAccount.getSalesWhatsAppNumber() != null){
                if(!dto.getSalesWhatsAppNumber().equals(newAccount.getSalesWhatsAppNumber())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("saleswhatsappnumber__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSalesWhatsAppNumber());
                    auditDto.setNewValue(newAccount.getSalesWhatsAppNumber());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSalesDistrict() != null && newAccount.getSalesDistrict() != null){
                if(!dto.getSalesDistrict().equals(newAccount.getSalesDistrict())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("salesdistrict__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSalesDistrict());
                    auditDto.setNewValue(newAccount.getSalesDistrict());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSalesOffice() != null && newAccount.getSalesOffice() != null){
                if(!dto.getSalesOffice().equals(newAccount.getSalesOffice())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("salesoffice__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSalesOffice());
                    auditDto.setNewValue(newAccount.getSalesOffice());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getDivision() != null && newAccount.getDivision() != null){
                if(!dto.getDivision().equals(newAccount.getDivision())){
                    InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                    auditDto.setFieldName("division__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getDivision());
                    auditDto.setNewValue(newAccount.getDivision());
                    auditDto.setRowId(dto.getProcRowId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

        }else{
            
            if(newAccount.getIntegrationId() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("integrationid");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getIntegrationId());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getCnpj() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("corporateregistrationnumber__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getCnpj());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getName() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("name");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getName());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getNickname() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("corporaterepresentativename__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getNickname());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getAccountStatus() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("cust_stat_cd__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getAccountStatus());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getReasonStatus() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("situationstatusreason__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getReasonStatus());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getDealerType() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("dealershoptype__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getDealerType());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getDealerCode() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("dealercode__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getDealerCode());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getMainPhone() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("phone");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getMainPhone());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getFax() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("fax");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getFax());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getMainEmailAddress() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("emailaddress__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getMainEmailAddress());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getWorkEmailAddress() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("sapworkaddress__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getWorkEmailAddress());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSalesDealerFlag() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("salesflag__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSalesDealerFlag());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getServiceDealerFlag() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("serviceflag__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getServiceDealerFlag());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getPartnerStartDate() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("prtnrshp_start_dt__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getPartnerStartDate());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getPartnerEndDate() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("prtnrshp_end_dt__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getPartnerEndDate());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }            

            if(newAccount.getRegion() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("region__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getRegion());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getWorkingHours() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("businesshours__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getWorkingHours());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getFacebookPage() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("facebook__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getFacebookPage());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getHomepage() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("website");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getHomepage());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getStreetAddress() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("billingstreet");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getStreetAddress());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getStreetAddress2() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("billingstreet_2__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getStreetAddress2());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getStreetAddress3() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("billingstreet_3__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getStreetAddress3());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getCounty() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("billingcounty");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getCounty());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getCity() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("billingcity");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getCity());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getState() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("billingstate");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getState());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getLatitude() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("billinglatitude");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(String.valueOf(newAccount.getLatitude()));
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getLongitude() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("billinglongitude");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(String.valueOf(newAccount.getLongitude()));
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getPostalCode() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("billingpostalcode");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getPostalCode());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getDealerGroup() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("dealergroupcode__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getDealerGroup());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSalesGroup() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("salesgroup__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSalesGroup());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSalesManager() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("salesmanager__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSalesManager());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getServiceManager() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("servicemanager__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getServiceManager());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getServiceWhatsAppNumber() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("servicewhatsappnumber__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getServiceWhatsAppNumber());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSalesWhatsAppNumber() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("saleswhatsappnumber__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSalesWhatsAppNumber());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSalesDistrict() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("salesdistrict__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSalesDistrict());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSalesOffice() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("getSalesOffice");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getIntegrationId());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getDivision() != null){
                InboundChannelPartnerAuditDto auditDto = new InboundChannelPartnerAuditDto();
                auditDto.setFieldName("division__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getDivision());
                auditDto.setRowId(newAccount.getProcRowId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

        }

    }
}
