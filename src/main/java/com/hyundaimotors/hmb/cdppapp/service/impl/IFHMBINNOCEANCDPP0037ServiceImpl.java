package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002.InboundContactAuditDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0037.IFHMBINNOCEANCDPP0037Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0037.ListOfAutoVehicleDto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0037Mapper;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.AutoVehicle;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ContactData;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ContactSocialMedia;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfAutoVehicle;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactSocialMedia;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0037Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0037ServiceImpl implements IFHMBINNOCEANCDPP0037Service{
    
    private final IFHMBINNOCEANCDPP0037Mapper mapper;

    public HashMap<String, Object> updateObject(IFHMBINNOCEANCDPP0037Dto dto)throws Exception{
        
        List<ListOfAutoVehicle> listVehicle = new ArrayList<>();
        List<ListOfContactSocialMedia> listSocialMedia = new ArrayList<>();
        
        ContactData updateContactOutput = new ContactData();  
        AutoVehicle autoVehicle = new AutoVehicle();
        ContactSocialMedia contactSocialMedia = new ContactSocialMedia();

        HashMap<String, String[]> processMap = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        List<ListOfAutoVehicleDto> listOfAutoVehicle = new ArrayList<>();         
        List<String> listVehicleId = new ArrayList<>();
        List<String> listParamId = new ArrayList<>();
        List<String> listAccountId = new ArrayList<>();
        List<String> listAssetId = new ArrayList<>();

        List<AutoVehicle> listOfAutoVehicleOut = new ArrayList<>();
        List<ContactSocialMedia> listOfContactSocialMediaOut = new ArrayList<>();

        IFHMBINNOCEANCDPP0037Dto auditAccount = new IFHMBINNOCEANCDPP0037Dto();

        auditAccount = mapper.getAccountProcess(dto);

        int checkContactId = mapper.checkAccountId(dto);

        if ( checkContactId > 0 ){

            mapper.insertObject(dto);
    
            if( dto.getListOfContactSocialMedia() != null ){
                mapper.insertSocialMedia(dto); 
            }
    
            listOfAutoVehicle = dto.getListOfAutoVehicle();
            listParamId.add(String.valueOf(dto.getRowId()));
            listAccountId.add(dto.getContactId());
    
            if( listOfAutoVehicle != null ){
                for(int i = 0; i < listOfAutoVehicle.size(); i++){
                    ListOfAutoVehicleDto vehicle = new ListOfAutoVehicleDto();
                    vehicle = listOfAutoVehicle.get(i);
                    vehicle.setParRowId(dto.getRowId());
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
            processMap.put("PROC_ACCOUNT_ID", accountId);
            // process asset rowId list
            processMap.put("ASSET_ID_LIST", assetId);
            
            mapper.transferProcess(processMap);    
            
            mapper.transferReplica(processMap);
            
            
            map.put("contactId", dto.getContactId());
            map.put("error_spcCode", "0");
            map.put("error_spcMessage", "OK");
            map.put("oldAccount", auditAccount);
            
            updateContactOutput = mapper.getUpdateContactOutput(dto);  
            if ( listOfAutoVehicle != null ){
                listVehicle = mapper.getListVehicle(processMap);
            }
            if (dto.getListOfContactSocialMedia() != null){
                listSocialMedia = mapper.getListSocialMedia(dto);
            }
            
            
            if( listVehicle != null){
                for( int i = 0; i < listVehicle.size(); i++){
                    autoVehicle.setAutoVehicle(listVehicle.get(i));
                    listOfAutoVehicleOut.add(autoVehicle);
                }
                if(listOfAutoVehicleOut != null){
                    updateContactOutput.setListOfAutoVehicle(listOfAutoVehicleOut);
                }
            }
    
            if( listSocialMedia != null){
                for( int i = 0; i < listSocialMedia.size(); i++){
                    contactSocialMedia.setContactSocialMedia(listSocialMedia.get(i));
                    listOfContactSocialMediaOut.add(contactSocialMedia);
                }
                if(listOfContactSocialMediaOut != null){
                    updateContactOutput.setListOfContactSocialMedia(listOfContactSocialMediaOut);
                }
            }
    
            map.put("updateContactOutput", updateContactOutput);
        }else {
            map.put("contactId", dto.getContactId());
            map.put("error_spcCode", "1");
            map.put("error_spcMessage", "No Data");
        }


        return map;
    }

    public void insertDPObject(HashMap<String, Object> resulMap)throws Exception{

        HashMap<String, String> map = new HashMap<>();

        map.put("PROC_CON_ID", String.valueOf(resulMap.get("contactId")));

        mapper.transferDPProcess(map);
       
        IFHMBINNOCEANCDPP0037Dto oldAccount = new IFHMBINNOCEANCDPP0037Dto();

        IFHMBINNOCEANCDPP0037Dto newAccount = new IFHMBINNOCEANCDPP0037Dto();

        oldAccount = (IFHMBINNOCEANCDPP0037Dto)resulMap.get("oldAccount");

        newAccount = (IFHMBINNOCEANCDPP0037Dto)resulMap.get("newAccount");

        List<InboundContactAuditDto> auditList = new ArrayList<>();

        if(oldAccount.getCnheXpirationDate() != null && newAccount.getCnheXpirationDate() != null){
            if(!oldAccount.getCnheXpirationDate().equals(newAccount.getCnheXpirationDate())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("cnhexpirationdate__c");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getCnheXpirationDate());
                auditDto.setNewValue(newAccount.getCnheXpirationDate());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getFirstName().equals(newAccount.getFirstName())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("firstname");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getFirstName());
                auditDto.setNewValue(newAccount.getFirstName());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getLastName().equals(newAccount.getLastName())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("lastname");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getLastName());
                auditDto.setNewValue(newAccount.getLastName());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getBirthDate().equals(newAccount.getBirthDate())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("personbirthdate");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getBirthDate());
                auditDto.setNewValue(newAccount.getBirthDate());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getGenre().equals(newAccount.getGenre())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("gender__pc");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getGenre());
                auditDto.setNewValue(newAccount.getGenre());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getHkmeRetailDate().equals(newAccount.getHkmeRetailDate())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("hkmeretaildate");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getHkmeRetailDate());
                auditDto.setNewValue(newAccount.getHkmeRetailDate());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getHomePhone().equals(newAccount.getHomePhone())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("personhomephone");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getHomePhone());
                auditDto.setNewValue(newAccount.getHomePhone());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getWorkPhone().equals(newAccount.getWorkPhone())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("workphone__c");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getWorkPhone());
                auditDto.setNewValue(newAccount.getWorkPhone());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getCellularPhone().equals(newAccount.getCellularPhone())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("personmobilephone");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getCellularPhone());
                auditDto.setNewValue(newAccount.getCellularPhone());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getEmailAddress().equals(newAccount.getEmailAddress())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("personemail");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getEmailAddress());
                auditDto.setNewValue(newAccount.getEmailAddress());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getLoyaltyOPTINFlag().equals(newAccount.getLoyaltyOPTINFlag())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("optinflag__c");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getLoyaltyOPTINFlag());
                auditDto.setNewValue(newAccount.getLoyaltyOPTINFlag());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getStreetAddress().equals(newAccount.getStreetAddress())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("billingstreet");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getStreetAddress());
                auditDto.setNewValue(newAccount.getStreetAddress());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getAddressNumber().equals(newAccount.getAddressNumber())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("billingstreet_2__c");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getAddressNumber());
                auditDto.setNewValue(newAccount.getAddressNumber());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getAddressComplement().equals(newAccount.getAddressComplement())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("billingstreet_3__c");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getAddressComplement());
                auditDto.setNewValue(newAccount.getAddressComplement());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getAddressCountry().equals(newAccount.getAddressCountry())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("billingcountry");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getAddressCountry());
                auditDto.setNewValue(newAccount.getAddressCountry());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getAddressCounty().equals(newAccount.getAddressCounty())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("neighborhood");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getAddressCounty());
                auditDto.setNewValue(newAccount.getAddressCounty());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getAddressCity().equals(newAccount.getAddressCity())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("billingcity");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getAddressCity());
                auditDto.setNewValue(newAccount.getAddressCity());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getAddressState().equals(newAccount.getAddressState())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("billingstate");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getAddressState());
                auditDto.setNewValue(newAccount.getAddressState());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getAddressPostalCode().equals(newAccount.getAddressPostalCode())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("billingpostalcode");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getAddressPostalCode());
                auditDto.setNewValue(newAccount.getAddressPostalCode());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getSuppressAllCalls().equals(newAccount.getSuppressAllCalls())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("persondonotcall");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getSuppressAllCalls());
                auditDto.setNewValue(newAccount.getSuppressAllCalls());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getSuppressAllCalls().equals(newAccount.getSuppressAllCalls())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("calloptyn__pc");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getSuppressAllCalls());
                auditDto.setNewValue(newAccount.getSuppressAllCalls());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getSuppressAllEmails().equals(newAccount.getSuppressAllEmails())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedemails__pc");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getSuppressAllEmails());
                auditDto.setNewValue(newAccount.getSuppressAllEmails());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getSuppressAllMailings().equals(newAccount.getSuppressAllMailings())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedletters__c");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getSuppressAllMailings());
                auditDto.setNewValue(newAccount.getSuppressAllMailings());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getSuppressAllMailings().equals(newAccount.getSuppressAllMailings())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedletters__pc");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getSuppressAllMailings());
                auditDto.setNewValue(newAccount.getSuppressAllMailings());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getSuppressAllMobile().equals(newAccount.getSuppressAllMobile())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedmobile__c");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getSuppressAllMobile());
                auditDto.setNewValue(newAccount.getSuppressAllMobile());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getSuppressAllMobile().equals(newAccount.getSuppressAllMobile())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedmobile__pc");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getSuppressAllMobile());
                auditDto.setNewValue(newAccount.getSuppressAllMobile());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getSuppressAllSMS().equals(newAccount.getSuppressAllSMS())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedsms__c");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getSuppressAllSMS());
                auditDto.setNewValue(newAccount.getSuppressAllSMS());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getSuppressAllSMS().equals(newAccount.getSuppressAllSMS())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedsms__pc");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getSuppressAllSMS());
                auditDto.setNewValue(newAccount.getSuppressAllSMS());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getSuppressAllVideoCall().equals(newAccount.getSuppressAllVideoCall())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedvideocall__c");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getSuppressAllVideoCall());
                auditDto.setNewValue(newAccount.getSuppressAllVideoCall());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getSuppressAllVideoCall().equals(newAccount.getSuppressAllVideoCall())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedvideocall__pc");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getSuppressAllVideoCall());
                auditDto.setNewValue(newAccount.getSuppressAllVideoCall());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getSuppressAllWhatsapp().equals(newAccount.getSuppressAllWhatsapp())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedwhatsapp__c");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getSuppressAllWhatsapp());
                auditDto.setNewValue(newAccount.getSuppressAllWhatsapp());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }

            if(!oldAccount.getSuppressAllWhatsapp().equals(newAccount.getSuppressAllWhatsapp())){
                InboundContactAuditDto auditDto = new InboundContactAuditDto();
                auditDto.setFieldName("blockedwhatsapp__pc");
                auditDto.setOperation("Modify");
                auditDto.setOldValue(oldAccount.getSuppressAllWhatsapp());
                auditDto.setNewValue(newAccount.getSuppressAllWhatsapp());
                auditDto.setRowId(oldAccount.getContactId());
                auditDto.setApiKey(newAccount.getApiKey());
                auditList.add(auditDto);
            }
        }

        mapper.insertAuditAccount(auditList);
    }
    
}
