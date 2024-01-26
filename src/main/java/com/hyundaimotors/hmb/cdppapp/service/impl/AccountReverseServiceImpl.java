package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.AccountReverse.AccountReverseDto;
import com.hyundaimotors.hmb.cdppapp.mapper.AccountReverseMapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBBLUELINKCDPP0053Mapper;
import com.hyundaimotors.hmb.cdppapp.service.AccountReverseService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountReverseServiceImpl implements AccountReverseService{
    
    private final AccountReverseMapper mapper;

    public AccountReverseDto insertObject(AccountReverseDto dto)throws Exception{
        AccountReverseDto resultDto = new AccountReverseDto();

        if("012Hs0000008kU4IAI".equals(dto.getRecordTypeId())){
            dto.setProcessAccountType("Person");
            mapper.insertAccount(dto);
            //mapper.insertDpContact(dto);
            //mapper.insertDpContactSub(dto);
        }else{
            dto.setProcessAccountType("Person");
            if(dto.getSfId().equals("012Hs0000008kTRIAY")){
                dto.setProcessAccountType("Business");
            }else{
                dto.setProcessAccountType("Dealer");
            }
            mapper.insertAccount(dto);
        }

        resultDto.setRowId(dto.getRowId());
        resultDto.setErrorSpcCode("0");
        resultDto.setErrorSpcMessage("OK");

        return resultDto;
    }
}
