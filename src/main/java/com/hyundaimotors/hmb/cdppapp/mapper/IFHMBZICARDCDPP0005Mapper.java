package com.hyundaimotors.hmb.cdppapp.mapper;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0005Dto;

public interface IFHMBZICARDCDPP0005Mapper {
    
    public int insertSoe(IFHMBZICARDCDPP0005Dto dto)throws Exception;

    public int insertSoex(IFHMBZICARDCDPP0005Dto dto)throws Exception;

    public int insertSapr(IFHMBZICARDCDPP0005Dto dto)throws Exception;

    public int updateSoe(IFHMBZICARDCDPP0005Dto dto)throws Exception;

    public int updateSoex(IFHMBZICARDCDPP0005Dto dto)throws Exception;

    public int updateSapr(IFHMBZICARDCDPP0005Dto dto)throws Exception;

}
