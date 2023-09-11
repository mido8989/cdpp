package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;
import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0022Dto;

public interface IFHMBINNOCEANCDPP0022Mapper {

    public List<IFHMBINNOCEANCDPP0022Dto> getCheckHoliDay(IFHMBINNOCEANCDPP0022Dto dto)throws Exception;

    public IFHMBINNOCEANCDPP0022Dto getRowId(IFHMBINNOCEANCDPP0022Dto dto)throws Exception;

    public int insertObject(IFHMBINNOCEANCDPP0022Dto dto)throws Exception;

    public void transferProcess(HashMap<String, String> parMap)throws Exception;

    public void transferReplica(HashMap<String, String> parMap)throws Exception;

}
