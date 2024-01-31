package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.AccountReverse.AccountReverseDto;
import com.hyundaimotors.hmb.cdppapp.mapper.AccountReverseMapper;
import com.hyundaimotors.hmb.cdppapp.service.AccountReverseService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountReverseServiceImpl implements AccountReverseService{
    
    private final AccountReverseMapper mapper;

    public AccountReverseDto insertObject(AccountReverseDto dto)throws Exception{
        AccountReverseDto resultDto = new AccountReverseDto();
        
        String rowId = mapper.getAccount(dto);
        // Person 일 경우
        
        if("012Dd00000006AUIAY".equals(dto.getRecordTypeId())){//Dev
        //if("012Hs0000008kU4IAI".equals(dto.getRecordTypeId())){//Pro
            if(dto.getExternalId() != null){
                if(rowId != null){
                    mapper.updateSflId(dto);
                }else{
                    dto.setProcessAccountType("Person");
                    mapper.insertAccount(dto);
                    mapper.insertDpContact(dto);
                    mapper.insertDpContactSub(dto);
                    //mapper.insertReplicaAcc(dto);
                }
            }else{
                dto.setProcessAccountType("Person");
                mapper.insertAccount(dto);
                mapper.insertDpContact(dto);
                mapper.insertDpContactSub(dto);
            }
            
        }else{
            // Business 일 경우
            if(dto.getSfId().equals("012Dd000000068OIAQ")){//Dev
            //if(dto.getSfId().equals("012Hs0000008kTRIAY")){//Pro
                if(dto.getExternalId() != null){
                    if(rowId != null){
                        mapper.updateSflId(dto);
                    }else{
                        dto.setProcessAccountType("Business");
                        mapper.insertAccount(dto);
                        mapper.insertDpOrgExt(dto);
                    }
                }else{
                    dto.setProcessAccountType("Business");
                    mapper.insertAccount(dto);
                    mapper.insertDpOrgExt(dto);
                }
            // Dealer 일 경우    
            }else{
                if(dto.getExternalId() != null){
                    if(rowId != null){
                        mapper.updateSflId(dto);
                    }else{
                        dto.setProcessAccountType("Dealer");
                        mapper.insertAccount(dto);
                        mapper.insertDpOrgExt(dto);
                    }
                }else{
                    dto.setProcessAccountType("Dealer");
                    mapper.insertAccount(dto);
                    mapper.insertDpOrgExt(dto);
                }
            }
            mapper.insertAccount(dto);
        }

        resultDto.setRowId(dto.getRowId());
        resultDto.setErrorSpcCode("0");
        resultDto.setErrorSpcMessage("OK");

        return resultDto;
    }
}
