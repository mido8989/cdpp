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

        mapper.insertObject(dto);
        
        /**
         * 20231122
         * 조건 : cnpjNumber 값이 있고 foundAccountIdbyCnpj조회 값이 있으면 update
         *       또는 name,mainPhoneNumber,mainEmailAddress 값이 있고 foundAccountIdbyCnpj조회 값이 있으면 update
         * 나머지 insert
         */
       boolean isValidation = false;
       
       String foundAccountIdbyCnpj = null;
       if(!isNull(dto.getCnpjNumber())) { //update
            isValidation = true;
           foundAccountIdbyCnpj = mapper.foundAccountIdbyCnpj(dto);
       }else {
           if(!isNull(dto.getName()) && !isNull(dto.getMainPhoneNumber()) && !isNull(dto.getMainEmailAddress())) {
               isValidation = true;
               foundAccountIdbyCnpj = mapper.foundAccountIdbyNameAndPhoneAndEmail(dto);
           }
       }

       String foundAccountId = mapper.foundAccountId(dto);

       IFHMBSAPCDPP0005Dto oldAccount = new IFHMBSAPCDPP0005Dto();

       oldAccount = mapper.getOldAccount(foundAccountId);

       
       if(isValidation) {
             if(!isNull(foundAccountIdbyCnpj)) {
                 resulDto.setContactId(update(dto,foundAccountIdbyCnpj));
                 resulDto.setCheckUpsert("update");
                 oldAccount.setCheckUpsert("update");
             }else {
                 resulDto.setContactId(insert(dto));
                 resulDto.setCheckUpsert("insert");
             }
             
             resulDto.setErrorSpcCode("0"); 
             resulDto.setErrorSpcMessage("OK");
       }else {
           if(!isNull(foundAccountId)) {//저장 조건중 row_id를 가져올수 있다면..
                resulDto.setContactId(update(dto,foundAccountId));
                resulDto.setCheckUpsert("update");
           }else {
               resulDto.setContactId(insert(dto));
               resulDto.setCheckUpsert("insert");
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
         
         String foundAccountId = mapper.foundAccountId(dto);
         
         map.put("PROC_ACC_ID", foundAccountId);
        //  mapper.insertReplicaAccount(map);
         
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
        //   mapper.insertReplicaAccount(map);
          
          return foundAccountIdbyCnpj;
    }
    
    /**
     * 공백이나 null체크
     * @param value
     * @return
     */
    private boolean isNull(String value) {
        return !(value != null && !"".equals(value.trim())) && true;
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
            if(dto.getCnpjNumber() != null && newAccount.getCnpjNumber() != null){
                if(!dto.getCnpjNumber().equals(newAccount.getCnpjNumber())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("corporateregistrationnumber__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getCnpjNumber());
                    auditDto.setNewValue(newAccount.getCnpjNumber());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(newAccount.getApiKey());
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

            if(dto.getIntegrationId() != null && newAccount.getIntegrationId() != null){
                if(!dto.getIntegrationId().equals(newAccount.getIntegrationId())){
                    AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                    auditDto.setFieldName("integrationid");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getIntegrationId());
                    auditDto.setNewValue(newAccount.getIntegrationId());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(newAccount.getApiKey());
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
        }else{
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

            if(newAccount.getIntegrationId() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("integrationid");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getIntegrationId());
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

            if(newAccount.getMainEmailAddress() != null){
                AccountWebserviceAuditDto auditDto = new AccountWebserviceAuditDto();
                auditDto.setFieldName("emailaddress__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getMainEmailAddress());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }
        }
        mapper.insertAuditList(auditList);

    }

}