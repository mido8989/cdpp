package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPCDPP0005Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBSAPCDPP0005Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPCDPP0005Service;
import com.hyundaimotors.hmb.cdppapp.util.JsonUtils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBSAPCDPP0005ServiceImpl implements IFHMBSAPCDPP0005Service{
    
    private final IFHMBSAPCDPP0005Mapper mapper;

    public IFHMBSAPCDPP0005Dto insertObject(IFHMBSAPCDPP0005Dto dto)throws Exception{
        IFHMBSAPCDPP0005Dto resulDto = new IFHMBSAPCDPP0005Dto();

        mapper.insertObject(dto); //=> return row_id
        
        /**
         * 20231121
         * 조건 : (cnpjNumber) 있거나 (name,mainPhoneNumber,mainEmailAddress) 값이 있으면 upsert
         * 그밖에 에러 처리 메시지 에러 코드는 1
         */
        boolean isValidation = false;
        
        if(!isNull(dto.getCnpjNumber())) { //update
            isValidation = true;
        }else {
           if(!isNull(dto.getName()) && !isNull(dto.getMainPhoneNumber()) && !isNull(dto.getMainEmailAddress())) {
               isValidation = true;
           }
        }
        
        if(isValidation) {
            HashMap<String, String> map = new HashMap<String,String>();
            map.put("PARAM_ID",    String.valueOf(dto.getRowId()));
            map.put("PROC_ACC_ID", mapper.getAccountRowId(dto));
            mapper.insertProcessAccount(map); //process.acount upsert
            
            map.put("PARAM_ID",    String.valueOf(dto.getRowId()));
            map.put("PROC_ACC_ID", mapper.getAccountRowId(dto));
            mapper.insertReplicaAccount(map); //replica.acount upsert
            
            resulDto.setErrorSpcCode("0");
            resulDto.setErrorSpcMessage("OK");
        }else {
            HashMap<String, String> map = new HashMap<String,String>();
            
            map.put("cnpjNumber", dto.getCnpjNumber());
            map.put("name",dto.getName());
            map.put("mainPhoneNumber",dto.getMainPhoneNumber());
            map.put("mainEmailAddress",dto.getMainEmailAddress());
            
            resulDto.setErrorSpcCode("1");
            resulDto.setErrorSpcMessage(JsonUtils.toJson(map));
        }
        return resulDto;
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