
package com.hyundaimotors.hmb.cdppapp.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002.IFHMBINNOCEANCDPP0002Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002.InboundContactAuditDto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0002Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0002Service;

import lombok.RequiredArgsConstructor;
@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0002ServiceImpl implements IFHMBINNOCEANCDPP0002Service{
    
    private final IFHMBINNOCEANCDPP0002Mapper mapper;
    public HashMap<String, IFHMBINNOCEANCDPP0002Dto> insertObject(IFHMBINNOCEANCDPP0002Dto dto)throws Exception{
        //To_Do : Return ContactId를 Process Id로 변경, Exception Runtime 으로 변경
    
        IFHMBINNOCEANCDPP0002Dto resultDto = new IFHMBINNOCEANCDPP0002Dto();
        String dtoLastName = dto.getLastName();
        String dtoFirstName = dto.getFirstName();
        // lastname 비어서 들어올 때 lastname 처리 
        if( dtoLastName == null || dtoLastName.isEmpty() || dtoLastName == ""){
            String[] splFirstName = dtoFirstName.split(" ",2);
            if ( splFirstName.length > 1){
                dto.setFirstName(splFirstName[0]);
                dto.setLastName(splFirstName[1]);
            }else{
                dto.setLastName(dtoFirstName);
            }
        }

        mapper.insertObject(dto);
        if(  dto.getListOfCurrentCars() != null){
            mapper.insertCar(dto);
        }
        if ( dto.getListOfApps() != null){
            mapper.insertApp(dto);
        }
        if(  dto.getListOfSocialMedia() != null){
            mapper.insertSocialmedia(dto);
        }
        if(  dto.getListOfHobby() != null){
            mapper.insertHobby(dto);            
        }
        if(  dto.getListOfSoccerTeam() != null){
            mapper.insertSoccerteam(dto);
        }
        
        
        /**
         * 20231122
         * 조건 : cpf 값이 있고 foundContactIdbyCpf조회 값이 있으면 update
         *       또는 name,phone,email 값이 있고 foundAccountIdbyNameAndPhoneAndEmail조회 값이 있으면 update
         * 나머지 insert
         */
       boolean isValidation = false;
       
       String foundContactIdbyCpf = null;
       if(!isNull(dto.getCpf())) { //update
            isValidation = true;
            foundContactIdbyCpf = mapper.foundContactIdbyCpf(dto);
       }else {
           if(!isNull(dto.getFirstName()) && !isNull(dto.getLastName()) && !isNull(dto.getCellPhone()) && !isNull(dto.getEmailAddress())) {
               isValidation = true;
               foundContactIdbyCpf = mapper.foundAccountIdbyNameAndPhoneAndEmail(dto);
           }
       }
       
       String foundContactId = mapper.foundContactId(dto);
       
       IFHMBINNOCEANCDPP0002Dto oldAccount = new IFHMBINNOCEANCDPP0002Dto();
       
       oldAccount = mapper.getOldAccount(foundContactId);

       if(isValidation) {
             if(!isNull(foundContactIdbyCpf)) {
                 resultDto.setContactId(update(dto,foundContactIdbyCpf));
                 resultDto.setCheckUpsert("update");
                 oldAccount.setCheckUpsert("update");
             }else {
                 resultDto.setContactId(insert(dto,foundContactId));
                 resultDto.setCheckUpsert("insert");
             }
             
             resultDto.setError_spcCode("0"); 
             resultDto.setError_spcMessage("OK");
       }else {
           if(!isNull(foundContactId)) {//저장 조건중 row_id를 가져올수 있다면..
               resultDto.setContactId(update(dto,foundContactId));
               resultDto.setCheckUpsert("update");
           }else {
               resultDto.setContactId(insert(dto,foundContactId));
               resultDto.setCheckUpsert("insert");
           }
           
           resultDto.setError_spcCode("0"); 
           resultDto.setError_spcMessage("OK");
       }
        
        HashMap<String, IFHMBINNOCEANCDPP0002Dto> resultMap = new HashMap<>();

        resultMap.put("resultDto", resultDto);
        resultMap.put("oldAccount", oldAccount);
        
        return resultMap;
    }
    
    /**
     * 
     * @param dto
     * @return
     * @throws Exception
     */
    private String insert(IFHMBINNOCEANCDPP0002Dto dto,String foundContactId) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("PARAM_ID", String.valueOf(dto.getRowId()));
        map.put("checkcu", "insert");
        
        mapper.transferProcess(map);
        
        String param_foundContactId = foundContactId;
        param_foundContactId = mapper.foundContactId(dto);
        map.put("CONTACT_ID", param_foundContactId);
        //mapper.transferReplica(map);
         
         return param_foundContactId;
    }
    
    /**
     * 
     * @param dto
     * @param foundAccountIdbyCnpj
     * @return
     * @throws Exception
     */
    private String update(IFHMBINNOCEANCDPP0002Dto dto,String foundContactId) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("PARAM_ID", String.valueOf(dto.getRowId()));
        map.put("CONTACT_ID", foundContactId);
        map.put("checkcu", "update");
        
        mapper.transferProcess(map);
        mapper.transferReplica(map);
          
        return foundContactId;
    }
    
    /**
     * 공백이나 null체크
     * @param value
     * @return
     */
    private boolean isNull(String value) {
        return !(value != null && !"".equals(value.trim())) && true;
    }


    public void insertDPObject(IFHMBINNOCEANCDPP0002Dto dto)throws Exception{

        HashMap<String, String> map = new HashMap<>();

        map.put("PARAM_ID", dto.getContactId());
        map.put("checkcu", dto.getCheckUpsert());

        mapper.transferDPProcess(map);

        IFHMBINNOCEANCDPP0002Dto newAccount = new IFHMBINNOCEANCDPP0002Dto();

        newAccount = mapper.getOldAccount(dto.getContactId());

        //String[] insertColumnValue = {"row_id" , "cpf__c" , "integrationid" , "firstname" , "lastname" , "personemail" , "personhomephone" , "workphone__c" , "personmobilephone" , "gender__pc" , "maritalstatus__pc" , "personbirthdate" , "occupation__pc" , "income__c" , "productofinterest__c" , "calledby__c" , "literacy__c" , "preferredcontactchannel__c" , "persondonotcall" , "calloptyn__pc" , "blockedemails__c" , "blockedemails__pc" , "blockedletters__c" , "blockedletters__pc" , "blockedmobile__c" , "blockedmobile__pc" , "blockedsms__c" , "blockedsms__pc" , "blockedvideocall__c" , "blockedvideocall__pc" , "blockedwhatsapp__c" , "blockedwhatsapp__pc" , "dealercode__c" , "registrysource__c" , "facebook__c" , "receiveproductnewsflag__c" , "receiveretailoffersflag__c" , "receiveserviceoffersflag__c" , "receivenewsletterflag__c" , "receiveeventsflag__c" , "receiveresearchflag__c" , "neighborhood"};
        //String[] updateColumnValue = {"cpf__c" , "integrationid" , "firstname" , "lastname" , "personemail" , "personhomephone" , "workphone__c" , "personmobilephone" , "gender__pc" , "maritalstatus__pc" , "personbirthdate" , "occupation__pc" , "income__c" , "productofinterest__c" , "calledby__c" , "literacy__c" , "preferredcontactchannel__c" , "persondonotcall" , "calloptyn__pc" , "blockedemails__c" , "blockedemails__pc" , "blockedletters__c" , "blockedletters__pc" , "blockedmobile__c" , "blockedmobile__pc" , "blockedsms__c" , "blockedsms__pc" , "blockedvideocall__c" , "blockedvideocall__pc" , "blockedwhatsapp__c" , "blockedwhatsapp__pc" , "dealercode__c" , "registrysource__c" , "facebook__c" , "receiveproductnewsflag__c" , "receiveretailoffersflag__c" , "receiveserviceoffersflag__c" , "receivenewsletterflag__c" , "receiveeventsflag__c" , "receiveresearchflag__c" , "neighborhood"};
        
        List<InboundContactAuditDto> auditList = new ArrayList<>();

        if("update".equals(dto.getCheckUpsert())){
            if(dto.getCpf() != null && newAccount.getCpf() != null){
                if(!dto.getCpf().equals(newAccount.getCpf())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("cpf__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getCpf());
                    auditDto.setNewValue(newAccount.getCpf());
                    auditDto.setRowId(dto.getContactId());
                    auditList.add(auditDto);
                }
            }

            if(dto.getIntegrationId() != null && newAccount.getIntegrationId() != null){
                if(!dto.getIntegrationId().equals(newAccount.getIntegrationId())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("integrationid");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getIntegrationId());
                    auditDto.setNewValue(newAccount.getIntegrationId());
                    auditDto.setRowId(dto.getContactId());
                    auditList.add(auditDto);
                }
            }
            if(dto.getFirstName() != null && newAccount.getFirstName() != null){
                if(!dto.getFirstName().equals(newAccount.getFirstName())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("firstname");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getFirstName());
                    auditDto.setNewValue(newAccount.getFirstName());
                    auditDto.setRowId(dto.getContactId());
                    auditList.add(auditDto);
                }
            }

            if(dto.getLastName() != null && newAccount.getLastName() != null){
                if(!dto.getLastName().equals(newAccount.getLastName())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("lastname");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getLastName());
                    auditDto.setNewValue(newAccount.getLastName());
                    auditDto.setRowId(dto.getContactId());
                    auditList.add(auditDto);
                }
            }

            if(dto.getEmailAddress() != null && newAccount.getEmailAddress() != null){
                if(!dto.getEmailAddress().equals(newAccount.getEmailAddress())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("personemail");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getEmailAddress());
                    auditDto.setNewValue(newAccount.getEmailAddress());
                    auditDto.setRowId(dto.getContactId());
                    auditList.add(auditDto);
                }
            }
        }else{
            if(newAccount.getCpf() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("cpf__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getCpf());
                auditDto.setRowId(newAccount.getContactId());
                auditList.add(auditDto);
            }

            if(newAccount.getIntegrationId() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("integrationid");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getIntegrationId());
                auditDto.setRowId(newAccount.getContactId());
                auditList.add(auditDto);
            }

            if(newAccount.getFirstName() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("firstname");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getFirstName());
                auditDto.setRowId(newAccount.getContactId());
                auditList.add(auditDto);
            }

            if(newAccount.getLastName() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("lastname");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getLastName());
                auditDto.setRowId(newAccount.getContactId());
                auditList.add(auditDto);
            }

            if(newAccount.getEmailAddress() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("personemail");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getEmailAddress());
                auditDto.setRowId(newAccount.getContactId());
                auditList.add(auditDto);
            }
        }
        mapper.insertAuditList(auditList);

    }
}
