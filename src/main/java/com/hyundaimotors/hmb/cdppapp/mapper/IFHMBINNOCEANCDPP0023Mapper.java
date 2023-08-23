package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0023Dto;

public interface IFHMBINNOCEANCDPP0023Mapper {
    
    public void insertObject(IFHMBINNOCEANCDPP0023Dto dto)throws Exception;

    public void insertDay(IFHMBINNOCEANCDPP0023Dto dto)throws Exception;

    public void transferProcess(HashMap<String, String> parMap)throws Exception;
}
