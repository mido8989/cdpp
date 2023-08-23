package com.hyundaimotors.hmb.cdppapp.mapper;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0025Dto;

public interface IFHMBINNOCEANCDPP0025Mapper {

    public int getCPFCheck(IFHMBINNOCEANCDPP0025Dto dto)throws Exception;
    
     public int insertObject(IFHMBINNOCEANCDPP0025Dto dto)throws Exception;

}
