package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0007Dto;

public interface IFHMBZICARDCDPP0007Mapper {
    
    public int getAssetExistNum(IFHMBZICARDCDPP0007Dto dto)throws Exception;

    public int getContactExistNum(IFHMBZICARDCDPP0007Dto dto)throws Exception;

    public int getAssetConNum(IFHMBZICARDCDPP0007Dto dto)throws Exception;

    public void insertObject(IFHMBZICARDCDPP0007Dto dto)throws Exception;
    
    public void updateObject(IFHMBZICARDCDPP0007Dto dto)throws Exception;

    public void transferProcess(HashMap<String, String> parMap)throws Exception;
    
}
