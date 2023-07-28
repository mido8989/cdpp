package com.hyundaimotors.hmb.cdppapp.mapper;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0027Dto;

public interface IFHMBINNOCEANCDPP0027Mapper {

    public int getParRowCheck(IFHMBINNOCEANCDPP0027Dto dto)throws Exception;

    public int insertObject(IFHMBINNOCEANCDPP0027Dto dto)throws Exception;
    
    public int updateObject(IFHMBINNOCEANCDPP0027Dto dto)throws Exception;
    
}
