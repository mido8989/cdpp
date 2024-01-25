
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
        }else if( dtoFirstName == null || dtoFirstName.isEmpty() || dtoFirstName == ""){
            String[] splLastName = dtoLastName.split(" ",2);
            if( splLastName.length > 1 ){
                dto.setFirstName(splLastName[0]);
                dto.setLastName(splLastName[1]);
            }else {
                dto.setFirstName(dtoLastName);
            }
        }

        // mobile 번호 형식 변경
        // String dtoCellPhone = dto.getCellPhone();
        // if( (dtoCellPhone != null && !dtoCellPhone.equals("")) && (dtoCellPhone.length() == 9 || dtoCellPhone.length() == 11)){
        //     dtoCellPhone = dtoCellPhone.replaceAll("[^0-9]", "");           
        //     if(dtoCellPhone.length() == 9){     // 9자리일 경우
        //         dtoCellPhone = "+55 00 " + dtoCellPhone.substring(0, 5) + " " + dtoCellPhone.substring(5, 9);
        //     }else{      // 11자리일 경우
        //         dtoCellPhone = "+55 " + dtoCellPhone.substring(0, 2) + " " + dtoCellPhone.substring(2, 7) + " " + dtoCellPhone.substring(7, 11);
        //     }
        //     dto.setCellPhone(dtoCellPhone);
        // }
        
        // cpf 숫자로만
        if(dto.getCpf() != null && !dto.getCpf().equals("")) { 
            dto.setCpf(dto.getCpf().replaceAll("[^0-9]", ""));
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
       String foundContactIdbyCpf = null; // cpf or email All check values
       if(dto.getCpf() != null && !dto.getCpf().equals("")) {   // cpf값이 들어왔을 때
            foundContactIdbyCpf = mapper.foundContactId(dto); 
            if(foundContactIdbyCpf != null){
                resultDto.setContactId(update(dto,foundContactIdbyCpf));
                resultDto.setCheckUpsert("update");
            }else {    
                resultDto.setContactId(insert(dto));
                resultDto.setCheckUpsert("insert");            
            }
            resultDto.setError_spcCode("0"); 
            resultDto.setError_spcMessage("OK");    
       }else{   // cpf값이 안들어왔을 때
            if( dto.getEmailAddress() != null && dto.getEmailAddress() != "" && dto.getCellPhone() != null && dto.getCellPhone() != "" ){   // Name, Email, Phone 모두 들어왔을 때
                dto.setFlgEmailAndPhone("Both");
                foundContactIdbyCpf = mapper.foundAccountIdbyNameAndPhoneAndEmail(dto);
                if(foundContactIdbyCpf != null){
                    resultDto.setContactId(update(dto,foundContactIdbyCpf));
                    resultDto.setCheckUpsert("update");
                }else{    
                    resultDto.setContactId(insert(dto));
                    resultDto.setCheckUpsert("insert");                        
                }
            }else if( (dto.getEmailAddress() != null && dto.getEmailAddress() != "") && (dto.getCellPhone() == null || dto.getCellPhone() == "") ){  // Name, Email 들어왔을 때
                dto.setFlgEmailAndPhone("Email");
                foundContactIdbyCpf = mapper.foundAccountIdbyNameAndPhoneAndEmail(dto);
                if(foundContactIdbyCpf != null){
                    resultDto.setContactId(update(dto,foundContactIdbyCpf));
                    resultDto.setCheckUpsert("update");       
                }else{    
                    resultDto.setContactId(insert(dto));
                    resultDto.setCheckUpsert("insert");                        
                }
            }else if((dto.getEmailAddress() == null || dto.getEmailAddress() == "" ) && (dto.getCellPhone() != null && dto.getCellPhone() != "") ){  // Name, Phone 들어왔을 때
                dto.setFlgEmailAndPhone("Phone");
                foundContactIdbyCpf = mapper.foundAccountIdbyNameAndPhoneAndEmail(dto);
                if(foundContactIdbyCpf != null){
                    resultDto.setContactId(update(dto,foundContactIdbyCpf));
                    resultDto.setCheckUpsert("update");   
                }else{    
                    resultDto.setContactId(insert(dto));
                    resultDto.setCheckUpsert("insert");                        
                }
            }
            resultDto.setError_spcCode("0"); 
            resultDto.setError_spcMessage("OK");
       }
       
       
       IFHMBINNOCEANCDPP0002Dto oldAccount = new IFHMBINNOCEANCDPP0002Dto();
       
       if (foundContactIdbyCpf != null){
           oldAccount = mapper.getOldAccount(foundContactIdbyCpf);           
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
    private String insert(IFHMBINNOCEANCDPP0002Dto dto) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("PARAM_ID", String.valueOf(dto.getRowId()));
        map.put("checkcu", "insert");
        
        mapper.transferProcess(map);
        
        String param_foundContactId = null;
        if(dto.getCpf() != null && !dto.getCpf().equals("")){
            param_foundContactId = mapper.foundContactId(dto); 
        }else{
            if( dto.getEmailAddress() != null && dto.getEmailAddress() != "" && dto.getCellPhone() != null && dto.getCellPhone() != ""  ){   // Name, Email, Phone 모두 들어왔을 때
                param_foundContactId = mapper.foundAccountIdbyNameAndPhoneAndEmail(dto);      
            }else if((dto.getEmailAddress() != null && dto.getEmailAddress() != "") && (dto.getCellPhone() == null || dto.getCellPhone() == "")  ){  // Name, Email 들어왔을 때
                param_foundContactId = mapper.foundAccountIdbyNameAndPhoneAndEmail(dto);               
            }else if( (dto.getEmailAddress() == null || dto.getEmailAddress() == "" ) && (dto.getCellPhone() != null && dto.getCellPhone() != "") ){  // Name, Phone 들어왔을 때
                param_foundContactId = mapper.foundAccountIdbyNameAndPhoneAndEmail(dto);
            }
        }
            
        map.put("CONTACT_ID", param_foundContactId);
        mapper.transferReplica(map);
         
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
                    auditDto.setApiKey(dto.getApiKey());
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
                    auditDto.setApiKey(dto.getApiKey());
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
                    auditDto.setApiKey(dto.getApiKey());
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
                    auditDto.setApiKey(dto.getApiKey());
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
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }
            
            if(dto.getHomePhone() != null && newAccount.getHomePhone() != null){
                if(!dto.getHomePhone().equals(newAccount.getHomePhone())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("personhomephone");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getHomePhone());
                    auditDto.setNewValue(newAccount.getHomePhone());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getWorkPhone() != null && newAccount.getWorkPhone() != null){
                if(!dto.getWorkPhone().equals(newAccount.getWorkPhone())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("workphone__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getWorkPhone());
                    auditDto.setNewValue(newAccount.getWorkPhone());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getCellPhone() != null && newAccount.getCellPhone() != null){
                if(!dto.getCellPhone().equals(newAccount.getCellPhone())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("personmobilephone");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getCellPhone());
                    auditDto.setNewValue(newAccount.getCellPhone());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getGender() != null && newAccount.getGender() != null){
                if(!dto.getGender().equals(newAccount.getGender())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("gender__pc");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getGender());
                    auditDto.setNewValue(newAccount.getGender());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getMaritalStatus() != null && newAccount.getMaritalStatus() != null){
                if(!dto.getMaritalStatus().equals(newAccount.getMaritalStatus())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("maritalstatus__pc");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getMaritalStatus());
                    auditDto.setNewValue(newAccount.getMaritalStatus());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getBirthDate() != null && newAccount.getBirthDate() != null){
                if(!dto.getBirthDate().equals(newAccount.getBirthDate())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("personbirthdate");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getBirthDate());
                    auditDto.setNewValue(newAccount.getBirthDate());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getOccupation() != null && newAccount.getOccupation() != null){
                if(!dto.getOccupation().equals(newAccount.getOccupation())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("occupation__pc");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getOccupation());
                    auditDto.setNewValue(newAccount.getOccupation());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getIncome() != null && newAccount.getIncome() != null){
                if(!dto.getIncome().equals(newAccount.getIncome())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("income__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getIncome());
                    auditDto.setNewValue(newAccount.getIncome());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getCarOfInterest() != null && newAccount.getCarOfInterest() != null){
                if(!dto.getCarOfInterest().equals(newAccount.getCarOfInterest())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("productofinterest__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getCarOfInterest());
                    auditDto.setNewValue(newAccount.getCarOfInterest());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getCalledBy() != null && newAccount.getCalledBy() != null){
                if(!dto.getCalledBy().equals(newAccount.getCalledBy())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("calledby__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getCalledBy());
                    auditDto.setNewValue(newAccount.getCalledBy());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getLiteracy() != null && newAccount.getLiteracy() != null){
                if(!dto.getLiteracy().equals(newAccount.getLiteracy())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("literacy__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getLiteracy());
                    auditDto.setNewValue(newAccount.getLiteracy());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getWayOfContact() != null && newAccount.getWayOfContact() != null){
                if(!dto.getWayOfContact().equals(newAccount.getWayOfContact())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("preferredcontactchannel__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getWayOfContact());
                    auditDto.setNewValue(newAccount.getWayOfContact());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSuppressAllCalls() != null && newAccount.getSuppressAllCalls() != null){
                if(!dto.getSuppressAllCalls().equals(newAccount.getSuppressAllCalls())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("persondonotcall");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSuppressAllCalls());
                    auditDto.setNewValue(newAccount.getSuppressAllCalls());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSuppressAllCalls() != null && newAccount.getSuppressAllCalls() != null){
                if(!dto.getSuppressAllCalls().equals(newAccount.getSuppressAllCalls())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("calloptyn__pc");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSuppressAllCalls());
                    auditDto.setNewValue(newAccount.getSuppressAllCalls());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSuppressAllEmails() != null && newAccount.getSuppressAllEmails() != null){
                if(!dto.getSuppressAllEmails().equals(newAccount.getSuppressAllEmails())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("blockedemails__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSuppressAllEmails());
                    auditDto.setNewValue(newAccount.getSuppressAllEmails());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSuppressAllEmails() != null && newAccount.getSuppressAllEmails() != null){
                if(!dto.getSuppressAllEmails().equals(newAccount.getSuppressAllEmails())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("blockedemails__pc");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSuppressAllEmails());
                    auditDto.setNewValue(newAccount.getSuppressAllEmails());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSuppressAllMailings() != null && newAccount.getSuppressAllMailings() != null){
                if(!dto.getSuppressAllMailings().equals(newAccount.getSuppressAllMailings())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("blockedletters__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSuppressAllMailings());
                    auditDto.setNewValue(newAccount.getSuppressAllMailings());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSuppressAllMailings() != null && newAccount.getSuppressAllMailings() != null){
                if(!dto.getSuppressAllMailings().equals(newAccount.getSuppressAllMailings())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("blockedletters__pc");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSuppressAllMailings());
                    auditDto.setNewValue(newAccount.getSuppressAllMailings());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSuppressAllMobile() != null && newAccount.getSuppressAllMobile() != null){
                if(!dto.getSuppressAllMobile().equals(newAccount.getSuppressAllMobile())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("blockedmobile__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSuppressAllMobile());
                    auditDto.setNewValue(newAccount.getSuppressAllMobile());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSuppressAllSMS() != null && newAccount.getSuppressAllSMS() != null){
                if(!dto.getSuppressAllSMS().equals(newAccount.getSuppressAllSMS())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("blockedmobile__pc");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSuppressAllSMS());
                    auditDto.setNewValue(newAccount.getSuppressAllSMS());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSuppressAllSMS() != null && newAccount.getSuppressAllSMS() != null){
                if(!dto.getSuppressAllSMS().equals(newAccount.getSuppressAllSMS())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("blockedsms__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSuppressAllSMS());
                    auditDto.setNewValue(newAccount.getSuppressAllSMS());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getCalledBy() != null && newAccount.getCalledBy() != null){
                if(!dto.getCalledBy().equals(newAccount.getCalledBy())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("blockedsms__pc");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getCalledBy());
                    auditDto.setNewValue(newAccount.getCalledBy());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSuppressAllVideoCall() != null && newAccount.getSuppressAllVideoCall() != null){
                if(!dto.getSuppressAllVideoCall().equals(newAccount.getSuppressAllVideoCall())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("blockedvideocall__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSuppressAllVideoCall());
                    auditDto.setNewValue(newAccount.getSuppressAllVideoCall());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSuppressAllVideoCall() != null && newAccount.getSuppressAllVideoCall() != null){
                if(!dto.getSuppressAllVideoCall().equals(newAccount.getSuppressAllVideoCall())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("blockedvideocall__pc");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSuppressAllVideoCall());
                    auditDto.setNewValue(newAccount.getSuppressAllVideoCall());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSuppressAllWhatsapp() != null && newAccount.getSuppressAllWhatsapp() != null){
                if(!dto.getSuppressAllWhatsapp().equals(newAccount.getSuppressAllWhatsapp())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("blockedwhatsapp__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSuppressAllWhatsapp());
                    auditDto.setNewValue(newAccount.getSuppressAllWhatsapp());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSuppressAllWhatsapp() != null && newAccount.getSuppressAllWhatsapp() != null){
                if(!dto.getSuppressAllWhatsapp().equals(newAccount.getSuppressAllWhatsapp())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("blockedwhatsapp__pc");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSuppressAllWhatsapp());
                    auditDto.setNewValue(newAccount.getSuppressAllWhatsapp());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getFavorite_dealer() != null && newAccount.getFavorite_dealer() != null){
                if(!dto.getFavorite_dealer().equals(newAccount.getFavorite_dealer())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("dealercode__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getFavorite_dealer());
                    auditDto.setNewValue(newAccount.getFavorite_dealer());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getSource() != null && newAccount.getSource() != null){
                if(!dto.getSource().equals(newAccount.getSource())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("registrysource__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getSource());
                    auditDto.setNewValue(newAccount.getSource());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getFacebookSegment() != null && newAccount.getFacebookSegment() != null){
                if(!dto.getFacebookSegment().equals(newAccount.getFacebookSegment())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("facebook__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getFacebookSegment());
                    auditDto.setNewValue(newAccount.getFacebookSegment());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getContactOptIn() != null && newAccount.getContactOptIn() != null){
                if(!dto.getContactOptIn().equals(newAccount.getContactOptIn())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("receiveproductnewsflag__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getContactOptIn());
                    auditDto.setNewValue(newAccount.getContactOptIn());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getContactOptIn() != null && newAccount.getContactOptIn() != null){
                if(!dto.getContactOptIn().equals(newAccount.getContactOptIn())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("receiveretailoffersflag__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getContactOptIn());
                    auditDto.setNewValue(newAccount.getContactOptIn());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getContactOptIn() != null && newAccount.getContactOptIn() != null){
                if(!dto.getContactOptIn().equals(newAccount.getContactOptIn())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("receiveserviceoffersflag__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getContactOptIn());
                    auditDto.setNewValue(newAccount.getContactOptIn());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getContactOptIn() != null && newAccount.getContactOptIn() != null){
                if(!dto.getContactOptIn().equals(newAccount.getContactOptIn())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("receivenewsletterflag__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getContactOptIn());
                    auditDto.setNewValue(newAccount.getContactOptIn());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getContactOptIn() != null && newAccount.getContactOptIn() != null){
                if(!dto.getContactOptIn().equals(newAccount.getContactOptIn())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("receiveeventsflag__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getContactOptIn());
                    auditDto.setNewValue(newAccount.getContactOptIn());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getContactOptIn() != null && newAccount.getContactOptIn() != null){
                if(!dto.getContactOptIn().equals(newAccount.getContactOptIn())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("receiveresearchflag__c");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getContactOptIn());
                    auditDto.setNewValue(newAccount.getContactOptIn());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
                    auditList.add(auditDto);
                }
            }

            if(dto.getCounty() != null && newAccount.getCounty() != null){
                if(!dto.getCounty().equals(newAccount.getCounty())){
                    InboundContactAuditDto auditDto = new InboundContactAuditDto();
                    auditDto.setFieldName("neighborhood");
                    auditDto.setOperation("Modify");
                    auditDto.setOldValue(dto.getCounty());
                    auditDto.setNewValue(newAccount.getCounty());
                    auditDto.setRowId(dto.getContactId());
                    auditDto.setApiKey(dto.getApiKey());
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
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getIntegrationId() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("integrationid");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getIntegrationId());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getFirstName() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("firstname");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getFirstName());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getLastName() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("lastname");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getLastName());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getEmailAddress() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("personemail");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getEmailAddress());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getHomePhone() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("personhomephone");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getHomePhone());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getWorkPhone() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("workphone__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getWorkPhone());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getCellPhone() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("personmobilephone");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getCellPhone());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getGender() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("gender__pc");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getGender());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getMaritalStatus() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("maritalstatus__pc");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getMaritalStatus());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getBirthDate() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("personbirthdate");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getBirthDate());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getOccupation() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("occupation__pc");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getOccupation());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getIncome() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("income__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getIncome());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getCarOfInterest() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("productofinterest__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getCarOfInterest());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getCalledBy() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("calledby__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getCalledBy());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getLiteracy() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("literacy__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getLiteracy());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getWayOfContact() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("preferredcontactchannel__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getWayOfContact());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSuppressAllCalls() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("persondonotcall");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSuppressAllCalls());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSuppressAllCalls() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("calloptyn__pc");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSuppressAllCalls());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSuppressAllEmails() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedemails__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSuppressAllEmails());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSuppressAllEmails() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedemails__pc");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSuppressAllEmails());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSuppressAllMailings() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedletters__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSuppressAllMailings());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSuppressAllMailings() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedletters__pc");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSuppressAllMailings());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSuppressAllMobile() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedmobile__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSuppressAllMobile());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSuppressAllMobile() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedmobile__pc");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSuppressAllMobile());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSuppressAllSMS() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedsms__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSuppressAllSMS());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSuppressAllSMS() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedsms__pc");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSuppressAllSMS());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSuppressAllVideoCall() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedvideocall__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSuppressAllVideoCall());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSuppressAllVideoCall() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedvideocall__pc");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSuppressAllVideoCall());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSuppressAllWhatsapp() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedwhatsapp__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSuppressAllWhatsapp());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSuppressAllWhatsapp() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedwhatsapp__pc");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSuppressAllWhatsapp());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getFavorite_dealer() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("dealercode__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getFavorite_dealer());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getSource() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("registrysource__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getSource());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getFacebookSegment() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("facebook__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getFacebookSegment());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getContactOptIn() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("receiveproductnewsflag__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getContactOptIn());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getContactOptIn() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("receiveretailoffersflag__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getContactOptIn());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getContactOptIn() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("receiveserviceoffersflag__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getContactOptIn());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getContactOptIn() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("receivenewsletterflag__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getContactOptIn());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getContactOptIn() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("receiveeventsflag__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getContactOptIn());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getContactOptIn() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("receiveresearchflag__c");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getContactOptIn());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }

            if(newAccount.getCounty() != null){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("neighborhood");
                auditDto.setOperation("New Record");
                auditDto.setNewValue(newAccount.getCounty());
                auditDto.setRowId(newAccount.getContactId());
                auditDto.setApiKey(dto.getApiKey());
                auditList.add(auditDto);
            }
        }
        mapper.insertAuditList(auditList);

    }
}
