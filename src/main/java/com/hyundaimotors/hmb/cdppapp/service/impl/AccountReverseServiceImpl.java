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

        if("012Hs0000008kU4IAI".equals(dto.getRecordTypeId())){
            if(dto.getExternalId() != null){
                if(rowId != null){
                    mapper.updateSflId(dto);
                }else{
                    dto.setProcessAccountType("Person");
                    mapper.insertAccount(dto);
                    mapper.insertDpContact(dto);
                    mapper.insertDpContactSub(dto);
                }
            }else{
                dto.setProcessAccountType("Person");
                mapper.insertAccount(dto);
                mapper.insertDpContact(dto);
                mapper.insertDpContactSub(dto);
            }
            
        }else{
            if(dto.getSfId().equals("012Hs0000008kTRIAY")){
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
                }
                
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
