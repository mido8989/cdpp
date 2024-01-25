package com.hyundaimotors.hmb.cdppapp.mapper;

import com.hyundaimotors.hmb.cdppapp.dto.AccountReverse.AccountReverseDto;

public interface AccountReverseMapper {
    
    public void insertPerson(AccountReverseDto dto)throws Exception;

    public void insertDealerOrBusiness(AccountReverseDto dto)throws Exception;
}
