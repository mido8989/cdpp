package com.hyundaimotors.hmb.cdppapp.mapper;

import com.hyundaimotors.hmb.cdppapp.dto.AccountReverse.AccountReverseDto;

public interface AccountReverseMapper {
    
    public void insertAccount(AccountReverseDto dto)throws Exception;

    public void insertDpContact(AccountReverseDto dto)throws Exception;

    public void insertDpContactSub(AccountReverseDto dto)throws Exception;
}
