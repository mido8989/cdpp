
package com.hyundaimotors.hmb.cdppapp.service.impl;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002.IFHMBINNOCEANCDPP0002Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0002Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0002Service;

import lombok.RequiredArgsConstructor;
@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0002ServiceImpl implements IFHMBINNOCEANCDPP0002Service{
    
    private final IFHMBINNOCEANCDPP0002Mapper mapper;
    public IFHMBINNOCEANCDPP0002Dto insertObject(IFHMBINNOCEANCDPP0002Dto dto)throws Exception{
        //To_Do : Return ContactId를 Process Id로 변경, Exception Runtime 으로 변경
    
        IFHMBINNOCEANCDPP0002Dto resultDto = new IFHMBINNOCEANCDPP0002Dto();

        // lastname 비어서 들어올 때 firstname을 lastname에 넣어주기
        if( dto.getLastName() == null || dto.getLastName().isEmpty() || dto.getLastName() == ""){
            dto.setLastName(dto.getFirstName());
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
       
       if(isValidation) {
             if(!isNull(foundContactIdbyCpf)) {
                 resultDto.setContactId(update(dto,foundContactIdbyCpf));
             }else {
                 resultDto.setContactId(insert(dto,foundContactId));
             }
             
             resultDto.setError_spcCode("0"); 
             resultDto.setError_spcMessage("OK");
       }else {
           if(!isNull(foundContactId)) {//저장 조건중 row_id를 가져올수 있다면..
               resultDto.setContactId(update(dto,foundContactId));
           }else {
               resultDto.setContactId(insert(dto,foundContactId));
           }
           
           resultDto.setError_spcCode("0"); 
           resultDto.setError_spcMessage("OK");
       }
        
        return resultDto;
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
        
        foundContactId = mapper.foundContactId(dto);
        map.put("CONTACT_ID", foundContactId);
        mapper.transferReplica(map);
         
         return foundContactId;
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
        return (value != null && !"".equals(value.trim()))?false:true;
    }
}
