package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0025Dto;

public interface IFHMBINNOCEANCDPP0025Mapper {

     public int insertObject(IFHMBINNOCEANCDPP0025Dto dto)throws Exception;

     public void transferProcess(HashMap<String, String> parMap)throws Exception;

}
