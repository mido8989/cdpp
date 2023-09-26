package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0023.EmpAvailabInDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0023.IFHMBINNOCEANCDPP0023Dto;

public interface IFHMBINNOCEANCDPP0023Mapper {
    
    public void insertObject(EmpAvailabInDto EmpAvailabIn)throws Exception;

    public void insertDay(EmpAvailabInDto EmpAvailabIn)throws Exception;

    public void transferProcess(HashMap<String, String> parMap)throws Exception;
}
