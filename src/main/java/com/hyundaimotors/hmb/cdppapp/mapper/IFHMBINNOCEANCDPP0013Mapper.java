package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Adress;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Contact;

public interface IFHMBINNOCEANCDPP0013Mapper {
    
    public int getRowId(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    public void insertAccount(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    public void insertAccountSub(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    public void insertAccountPrtnr(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    public void insertAddress(IFHMBINNOCEANCDPP0013Adress address)throws Exception;

    public void insertContact(IFHMBINNOCEANCDPP0013Contact contact)throws Exception;

    public void updateAccount(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    public void updateAccountSub(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    public void updateAccountPrtnr(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    public void updateContact(IFHMBINNOCEANCDPP0013Contact contact)throws Exception;

    public void updateAddress(IFHMBINNOCEANCDPP0013Adress address)throws Exception;
}
