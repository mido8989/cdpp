package com.hyundaimotors.hmb.cdppapp.mapper;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0028Dto;

public interface IFHMBINNOCEANCDPP0028Mapper {
    
    public int checkRowId(IFHMBINNOCEANCDPP0028Dto dto)throws Exception;

    public void insertObject(IFHMBINNOCEANCDPP0028Dto dto)throws Exception;

    public void updateObject(IFHMBINNOCEANCDPP0028Dto dto)throws Exception;
}
