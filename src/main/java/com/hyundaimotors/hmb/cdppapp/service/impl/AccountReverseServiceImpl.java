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
        if("012Hs0000008kU4IAI".equals(dto.getRecordTypeId())){
            if(dto.getExternalId() != null){
                if(rowId != null){
                    System.out.print("dto row_id 01::::::::::::::::::::::" + dto.getRowId());
                    mapper.updateSflId(dto);
                    System.out.print("dto row_id 02::::::::::::::::::::::" + dto.getRowId());
                }else{
                    dto.setProcessAccountType("Person");
                    System.out.print("dto row_id 03::::::::::::::::::::::" + dto.getRowId());
                    mapper.insertAccount(dto);
                    System.out.print("dto row_id 04::::::::::::::::::::::" + dto.getRowId());
                    mapper.insertDpContact(dto);
                    mapper.insertDpContactSub(dto);
                    mapper.insertReplicaAcc(dto);
                }
            }else{
                dto.setProcessAccountType("Person");
                mapper.insertAccount(dto);
                System.out.print("dto row_id 05::::::::::::::::::::::" + dto.getRowId());
                mapper.insertDpContact(dto);
                System.out.print("dto row_id 06::::::::::::::::::::::" + dto.getRowId());
                mapper.insertDpContactSub(dto);
            }
            
        }else{
            // Business 일 경우
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
