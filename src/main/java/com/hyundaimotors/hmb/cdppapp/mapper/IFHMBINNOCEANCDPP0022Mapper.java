package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0022Dto;

public interface IFHMBINNOCEANCDPP0022Mapper {

    public List<IFHMBINNOCEANCDPP0022Dto> getRowId(IFHMBINNOCEANCDPP0022Dto dto)throws Exception;

    public int insertObject(IFHMBINNOCEANCDPP0022Dto dto)throws Exception;

    public int deleteObject(IFHMBINNOCEANCDPP0022Dto dto)throws Exception;
}
