package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0013Mapper;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Adress;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Contact;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0013Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0013ServiceImpl implements IFHMBINNOCEANCDPP0013Service{
    
    private final IFHMBINNOCEANCDPP0013Mapper mapper;   

    public IFHMBINNOCEANCDPP0013Dto upsertObject(IFHMBINNOCEANCDPP0013Dto dto)throws Exception{
        List<IFHMBINNOCEANCDPP0013Adress> addressList = new ArrayList<>();
        List<IFHMBINNOCEANCDPP0013Contact> contactList = new ArrayList<>();

        addressList = dto.getAddressList();
        contactList = dto.getContactList();
        
        IFHMBINNOCEANCDPP0013Dto resultDto = new IFHMBINNOCEANCDPP0013Dto();
        int checkRowNum = mapper.getRowId(dto);
        

        if(0 == checkRowNum){
            mapper.insertAccount(dto);
            mapper.insertAccountSub(dto);
            mapper.insertAccountPrtnr(dto);
            
            HashMap<String, String> map = new HashMap<>();
            map.put("PARAM_ID", dto.getRowId());
            map.put("checkcu", "insert");

            mapper.transferProcess(map);

            for(int i=0; i < addressList.size(); i++){
                IFHMBINNOCEANCDPP0013Adress adress = new IFHMBINNOCEANCDPP0013Adress();
                adress = addressList.get(i);
                mapper.insertAddress(adress);
                IFHMBINNOCEANCDPP0013Contact contact = new IFHMBINNOCEANCDPP0013Contact();
                contact = contactList.get(i);
                contact.setAddressRowId(adress.getAddressRowId());
                mapper.insertContact(contact);

                HashMap<String, String> contactMap = new HashMap<>();
                contactMap.put("PARAM_ID", dto.getContactRowId());
                contactMap.put("checkcu", "insert");
                mapper.transferProcessContact(map);                
            }

           
        }else{
            mapper.updateAccount(dto);
            mapper.updateAccountSub(dto);
            mapper.updateAccountPrtnr(dto);
            for(int i=0; i < contactList.size(); i++){
                IFHMBINNOCEANCDPP0013Contact contact = new IFHMBINNOCEANCDPP0013Contact();
                contact = contactList.get(i);
                mapper.updateContact(contact);
                IFHMBINNOCEANCDPP0013Adress adress = new IFHMBINNOCEANCDPP0013Adress();
                adress = addressList.get(i);
                adress.setAddressRowId(contact.getAddressRowId());
                mapper.updateAddress(adress);
            }
        }

        resultDto.setErrorSpcCode("0");
        resultDto.setErrorSpcMessage("OK");
        
        return resultDto;
    }
}
