package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPCDPP0005.AccountWebserviceAuditDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPCDPP0005.IFHMBSAPCDPP0005Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBSAPCDPP0005Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPCDPP0005Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBSAPCDPP0005ServiceImpl implements IFHMBSAPCDPP0005Service{
    
    private final IFHMBSAPCDPP0005Mapper mapper;

    public HashMap<String, IFHMBSAPCDPP0005Dto> insertObject(IFHMBSAPCDPP0005Dto dto)throws Exception{
        IFHMBSAPCDPP0005Dto resulDto = new IFHMBSAPCDPP0005Dto();

        if(dto.getCnpjNumber() != null && dto.getCheckUpsert() != ""){
            dto.setCnpjNumber(dto.getCnpjNumber().replaceAll("[^0-9]", ""));
        }

        mapper.insertObject(dto);
        
       IFHMBSAPCDPP0005Dto oldAccount = new IFHMBSAPCDPP0005Dto();
       String foundAccountIdbyCnpj = null;

       if(dto.getCnpjNumber() != null && !dto.getCnpjNumber().equals("")) { // cnpjNumber가 들어 왔을 때
            foundAccountIdbyCnpj = mapper.foundAccountId(dto);
            if ( foundAccountIdbyCnpj != null ){
                resulDto.setContactId(update(dto,foundAccountIdbyCnpj));
                resulDto.setCheckUpsert("update");
                oldAccount = mapper.getOldAccount(foundAccountIdbyCnpj);
                oldAccount.setCheckUpsert("update");
            }else{                  
                resulDto.setContactId(insert(dto));
                resulDto.setCheckUpsert("insert"); 
            }              
            resulDto.setErrorSpcCode("0"); 
            resulDto.setErrorSpcMessage("OK");
        }else{  // cnpjNumber가 안들어 왔을 때
            if(dto.getMainEmailAddress() != null && dto.getMainEmailAddress() != "" && dto.getMainPhoneNumber() != null && dto.getMainPhoneNumber() != ""){ // Email, Phone 둘 다 들어왔을 때
                dto.setFlgEmailAndPhone("Both");
                foundAccountIdbyCnpj = mapper.foundAccountIdbyNameAndPhoneAndEmail(dto);
                if( foundAccountIdbyCnpj != null ){
                    resulDto.setContactId(update(dto,foundAccountIdbyCnpj));
                    resulDto.setCheckUpsert("update");
                    oldAccount = mapper.getOldAccount(foundAccountIdbyCnpj);
                    oldAccount.setCheckUpsert("update");
                }else{
                    resulDto.setContactId(insert(dto));
                    resulDto.setCheckUpsert("insert"); 
                }
            }else if(dto.getMainEmailAddress() != null && dto.getMainEmailAddress() != "" && (dto.getMainPhoneNumber() == null || dto.getMainPhoneNumber() == "")){ // Email만 들어왔을 때
                dto.setFlgEmailAndPhone("Email");
                foundAccountIdbyCnpj = mapper.foundAccountIdbyNameAndPhoneAndEmail(dto);
                if( foundAccountIdbyCnpj != null ){
                    resulDto.setContactId(update(dto,foundAccountIdbyCnpj));
                    resulDto.setCheckUpsert("update");
                    oldAccount = mapper.getOldAccount(foundAccountIdbyCnpj);
                    oldAccount.setCheckUpsert("update");
                }else{
                    resulDto.setContactId(insert(dto));
                    resulDto.setCheckUpsert("insert"); 
                }
            }else if((dto.getMainEmailAddress() == null || dto.getMainEmailAddress() == "" )&& dto.getMainPhoneNumber() != null && dto.getMainPhoneNumber() != ""){ // Phone만 들어왔을 때 
                dto.setFlgEmailAndPhone("Phone");
                foundAccountIdbyCnpj = mapper.foundAccountIdbyNameAndPhoneAndEmail(dto);
                if( foundAccountIdbyCnpj != null ){
                    resulDto.setContactId(update(dto,foundAccountIdbyCnpj));
                    resulDto.setCheckUpsert("update");
                    oldAccount = mapper.getOldAccount(foundAccountIdbyCnpj);
                    oldAccount.setCheckUpsert("update");
                }else{
                    resulDto.setContactId(insert(dto));
                    resulDto.setCheckUpsert("insert"); 
                }
            }

            resulDto.setErrorSpcCode("0"); 
            resulDto.setErrorSpcMessage("OK");
        }


       HashMap<String, IFHMBSAPCDPP0005Dto> resultMap = new HashMap<>();

       resultMap.put("resultDto", resulDto);
       resultMap.put("oldAccount", oldAccount);

       return resultMap;
    }
    
    /**
     * 
     * @param dto
     * @return
     * @throws Exception
     */
    private String insert(IFHMBSAPCDPP0005Dto dto) throws Exception {
         HashMap<String, String> map = new HashMap<>();
         map.put("PARAM_ID", String.valueOf(dto.getRowId()));
         map.put("checkcu","insert");
         
         mapper.insertProcessAccount(map);  
         
         String foundAccountId = null;
         if( dto.getCnpjNumber() != null && dto.getCnpjNumber() != ""){
            foundAccountId = mapper.foundAccountId(dto);
         }else{
            if(dto.getMainEmailAddress() != null && dto.getMainEmailAddress() != "" && dto.getMainPhoneNumber() != null && dto.getMainPhoneNumber() != ""){ // Email, Phone 둘 다 들어왔을 때
                foundAccountId = mapper.foundAccountIdbyNameAndPhoneAndEmail(dto);               
            }else if(dto.getMainEmailAddress() != null && dto.getMainEmailAddress() != "" && (dto.getMainPhoneNumber() == null || dto.getMainPhoneNumber() == "")){ // Email만 들어왔을 때
                foundAccountId = mapper.foundAccountIdbyNameAndPhoneAndEmail(dto);               
            }else if((dto.getMainEmailAddress() == null || dto.getMainEmailAddress() == "" )&& dto.getMainPhoneNumber() != null && dto.getMainPhoneNumber() != ""){ // Phone만 들어왔을 때 
                foundAccountId = mapper.foundAccountIdbyNameAndPhoneAndEmail(dto);               
            }
         }
                  
         map.put("PROC_ACC_ID", foundAccountId);         
         mapper.insertReplicaAccount(map);
         
         return foundAccountId;
    }
    
    /**
     * 
     * @param dto
     * @param foundAccountIdbyCnpj
     * @return
     * @throws Exception
     */
    private String update(IFHMBSAPCDPP0005Dto dto,String foundAccountIdbyCnpj) throws Exception {
          HashMap<String, String> map = new HashMap<>();
          map.put("PARAM_ID", String.valueOf(dto.getRowId()));
          map.put("PROC_ACC_ID", foundAccountIdbyCnpj);
          map.put("checkcu","update");
          
          mapper.insertProcessAccount(map);
          mapper.insertReplicaAccount(map);
          
          return foundAccountIdbyCnpj;
    }


    public void insertDPObject(IFHMBSAPCDPP0005Dto dto)throws Exception{

        HashMap<String, String> map = new HashMap<>();

        map.put("PARAM_ID", dto.getContactId());
        map.put("checkcu", dto.getCheckUpsert());

        mapper.transferDPProcess(map);

        IFHMBSAPCDPP0005Dto newAccount = new IFHMBSAPCDPP0005Dto();

        newAccount = mapper.getOldAccount(dto.getContactId());

        List<AccountWebserviceAuditDto> auditList = new ArrayList<>();

        if("update".equals(dto.getCheckUpsert())){
            if(dto.getIntegrationId() != null && newAccount.getIntegrationId() != null){
                if(!dto.getIntegrationId().equals(newAccount.getIntegrationId())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("integrationid");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getIntegrationId());
                    auditDto.setNewValue(newAccount.getIntegrationId());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getCnpjNumber() != null && newAccount.getCnpjNumber() != null){
                if(!dto.getCnpjNumber().equals(newAccount.getCnpjNumber())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("corporateregistrationnumber__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getCnpjNumber());
                    auditDto.setNewValue(newAccount.getCnpjNumber());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getName() != null && newAccount.getName() != null){
                if(!dto.getName().equals(newAccount.getName())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("name");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getName());
                    auditDto.setNewValue(newAccount.getName());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getNickname() != null && newAccount.getNickname() != null){
                if(!dto.getNickname().equals(newAccount.getNickname())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("corporaterepresentativename__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getNickname());
                    auditDto.setNewValue(newAccount.getNickname());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getAgencyCode() != null && newAccount.getAgencyCode() != null){
                if(!dto.getAgencyCode().equals(newAccount.getAgencyCode())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("ori_cd__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getAgencyCode());
                    auditDto.setNewValue(newAccount.getAgencyCode());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }         
            
            if(dto.getFleetAccontType() != null && newAccount.getFleetAccontType() != null){
                if(!dto.getFleetAccontType().equals(newAccount.getFleetAccontType())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("businesstype__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getFleetAccontType());
                    auditDto.setNewValue(newAccount.getFleetAccontType());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }   

            if(dto.getIndustryTrend() != null && newAccount.getIndustryTrend() != null){
                if(!dto.getIndustryTrend().equals(newAccount.getIndustryTrend())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("industry");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getIndustryTrend());
                    auditDto.setNewValue(newAccount.getIndustryTrend());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }    

            if(dto.getNumberOfEmployee() != null && newAccount.getNumberOfEmployee() != null){
                if(!dto.getNumberOfEmployee().equals(newAccount.getNumberOfEmployee())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("numberofemployees");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getNumberOfEmployee());
                    auditDto.setNewValue(newAccount.getNumberOfEmployee());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }    

            if(dto.getTotalFleetSize() != null && newAccount.getTotalFleetSize() != null){
                if(!dto.getTotalFleetSize().equals(newAccount.getTotalFleetSize())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("x_tot_flt_size__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(String.valueOf(dto.getTotalFleetSize()));
                    auditDto.setNewValue(String.valueOf(newAccount.getTotalFleetSize()));
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }    

            if(dto.getContractedSince() != null && newAccount.getContractedSince() != null){
                if(!dto.getContractedSince().equals(newAccount.getContractedSince())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("x_contracted_since__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getContractedSince());
                    auditDto.setNewValue(newAccount.getContractedSince());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSource() != null && newAccount.getSource() != null){
                if(!dto.getSource().equals(newAccount.getSource())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("registrysource__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSource());
                    auditDto.setNewValue(newAccount.getSource());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getOrganizationType() != null && newAccount.getOrganizationType() != null){
                if(!dto.getOrganizationType().equals(newAccount.getOrganizationType())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("fleettype__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getOrganizationType());
                    auditDto.setNewValue(newAccount.getOrganizationType());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getMainPhoneNumber() != null && newAccount.getMainPhoneNumber() != null){
                if(!dto.getMainPhoneNumber().equals(newAccount.getMainPhoneNumber())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("phone");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getMainPhoneNumber());
                    auditDto.setNewValue(newAccount.getMainPhoneNumber());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getMainFaxNumber() != null && newAccount.getMainFaxNumber() != null){
                if(!dto.getMainFaxNumber().equals(newAccount.getMainFaxNumber())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("fax");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getMainFaxNumber());
                    auditDto.setNewValue(newAccount.getMainFaxNumber());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getMainEmailAddress() != null && newAccount.getMainEmailAddress() != null){
                if(!dto.getMainEmailAddress().equals(newAccount.getMainEmailAddress())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("emailaddress__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getMainEmailAddress());
                    auditDto.setNewValue(newAccount.getMainEmailAddress());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getHomepage() != null && newAccount.getHomepage() != null){
                if(!dto.getHomepage().equals(newAccount.getHomepage())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("website");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getHomepage());
                    auditDto.setNewValue(newAccount.getHomepage());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getStreetAddress() != null && newAccount.getStreetAddress() != null){
                if(!dto.getStreetAddress().equals(newAccount.getStreetAddress())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("billingstreet");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getStreetAddress());
                    auditDto.setNewValue(newAccount.getStreetAddress());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getStreetAddress2() != null && newAccount.getStreetAddress2() != null){
                if(!dto.getStreetAddress2().equals(newAccount.getStreetAddress2())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("billingstreet_2__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getStreetAddress2());
                    auditDto.setNewValue(newAccount.getStreetAddress2());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getStreetAddress3() != null && newAccount.getStreetAddress3() != null){
                if(!dto.getStreetAddress3().equals(newAccount.getStreetAddress3())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("billingstreet_3__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getStreetAddress3());
                    auditDto.setNewValue(newAccount.getStreetAddress3());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getCounty() != null && newAccount.getCounty() != null){
                if(!dto.getCounty().equals(newAccount.getCounty())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("neighborhood");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getCounty());
                    auditDto.setNewValue(newAccount.getCounty());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getCity() != null && newAccount.getCity() != null){
                if(!dto.getCity().equals(newAccount.getCity())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("billingcity");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getCity());
                    auditDto.setNewValue(newAccount.getCity());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getState() != null && newAccount.getState() != null){
                if(!dto.getState().equals(newAccount.getState())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("billingstate");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getState());
                    auditDto.setNewValue(newAccount.getState());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getPostalCode() != null && newAccount.getPostalCode() != null){
                if(!dto.getPostalCode().equals(newAccount.getPostalCode())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("billingpostalcode");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getPostalCode());
                    auditDto.setNewValue(newAccount.getPostalCode());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getDescription() != null && newAccount.getDescription() != null){
                if(!dto.getDescription().equals(newAccount.getDescription())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("description");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getDescription());
                    auditDto.setNewValue(newAccount.getDescription());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getParentId() != null && newAccount.getParentId() != null){
                if(!dto.getParentId().equals(newAccount.getParentId())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("parentid");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getParentId());
                    auditDto.setNewValue(newAccount.getParentId());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

        }else{

            if(newAccount.getIntegrationId() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("integrationid");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getIntegrationId());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getCnpjNumber() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("corporateregistrationnumber__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getCnpjNumber());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getName() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("name");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getName());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getNickname() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("corporaterepresentativename__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getNickname());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }           

            if(newAccount.getAgencyCode() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("ori_cd__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getAgencyCode());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }           

            if(newAccount.getFleetAccontType() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("businesstype__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getFleetAccontType());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }           

            if(newAccount.getIndustryTrend() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("industry");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getIndustryTrend());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }           

            if(newAccount.getNumberOfEmployee() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("numberofemployees");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getNumberOfEmployee());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }           

            if(newAccount.getTotalFleetSize() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("x_tot_flt_size__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(String.valueOf(newAccount.getTotalFleetSize()));
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }           

            if(newAccount.getContractedSince() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("x_contracted_since__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getContractedSince());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }           

            if(newAccount.getSource() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("registrysource__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSource());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }           

            if(newAccount.getOrganizationType() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("fleettype__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getOrganizationType());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }           

            if(newAccount.getMainPhoneNumber() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("phone");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getMainPhoneNumber());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getMainFaxNumber() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("fax");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getMainFaxNumber());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }    

            if(newAccount.getMainEmailAddress() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("emailaddress__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getMainEmailAddress());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getHomepage() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("website");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getHomepage());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getStreetAddress() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("billingstreet");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getStreetAddress());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getStreetAddress2() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("billingstreet_2__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getStreetAddress2());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getStreetAddress3() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("billingstreet_3__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getStreetAddress3());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getCounty() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("neighborhood");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getCounty());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getCity() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("billingcity");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getCity());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getState() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("billingstate");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getState());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getPostalCode() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("billingpostalcode");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getPostalCode());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getDescription() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("description");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getDescription());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getParentId() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("parentid");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getParentId());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }
        }
        mapper.insertAuditList(auditList);

    }

}