package com.hyundaimotors.hmb.cdppapp.mapper;

import com.hyundaimotors.hmb.cdppapp.dto.AccountReverse.AccountReverseDto;

public interface AccountReverseMapper {
    
    public void insertAccount(AccountReverseDto dto)throws Exception;

    public void insertDpContact(AccountReverseDto dto)throws Exception;

    public void insertDpContactSub(AccountReverseDto dto)throws Exception;

    public String getAccount(AccountReverseDto dto)throws Exception;

    public void updateSflId(AccountReverseDto dto)throws Exception;

    public void insertDpOrgExt(AccountReverseDto dto)throws Exception;

    public void insertDpOrgExtSub(AccountReverseDto dto)throws Exception;

    public void insertReplicaAcc(AccountReverseDto dto)throws Exception;
}
