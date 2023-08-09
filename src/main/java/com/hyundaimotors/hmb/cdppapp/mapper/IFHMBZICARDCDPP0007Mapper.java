package com.hyundaimotors.hmb.cdppapp.mapper;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0007Dto;

public interface IFHMBZICARDCDPP0007Mapper {
    
    public int getParRowCheck(IFHMBZICARDCDPP0007Dto dto)throws Exception;

    public int insertObject(IFHMBZICARDCDPP0007Dto dto)throws Exception;
    
    public int updateObject(IFHMBZICARDCDPP0007Dto dto)throws Exception;


}
