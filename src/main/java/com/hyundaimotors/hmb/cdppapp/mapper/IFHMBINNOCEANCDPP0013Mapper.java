package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;
import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Adress;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Contact;

public interface IFHMBINNOCEANCDPP0013Mapper {
    
    public int getRowId(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    public void insertBusinessAccount(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;
    
    public void insertAdress(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    public void insertPersonAccount(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    public void insertType(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    public void insertPosition(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    public void transferProcess(HashMap<String, String> parMap)throws Exception;

    public void transferProcessContact(HashMap<String, String> parMap)throws Exception;

    public void updateAccount(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    public void updateAccountSub(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;
}
