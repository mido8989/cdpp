package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBMAPFRECDPP0009Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBMAPFRECDPP0009RSAServicePayDto;

public interface IFHMBMAPFRECDPP0009Mapper {
    
    public int getAssetCount(IFHMBMAPFRECDPP0009Dto dto)throws Exception;

    public int getCheckoutRowId(IFHMBMAPFRECDPP0009Dto dto)throws Exception;

    public int insertObject(IFHMBMAPFRECDPP0009Dto dto)throws Exception;

    public int updateParRowId(IFHMBMAPFRECDPP0009Dto dto)throws Exception;

    public int updateObject(IFHMBMAPFRECDPP0009Dto dto)throws Exception;

    public int rasServiceCheck(IFHMBMAPFRECDPP0009RSAServicePayDto dto)throws Exception;

    public int insertResSetvice(IFHMBMAPFRECDPP0009RSAServicePayDto dto)throws Exception;

    public int updateResSetvice(IFHMBMAPFRECDPP0009RSAServicePayDto dto)throws Exception;

    public void transferProcess(HashMap<String, String> parMap)throws Exception;
}
