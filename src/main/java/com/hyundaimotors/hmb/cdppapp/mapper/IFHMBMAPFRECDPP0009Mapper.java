package com.hyundaimotors.hmb.cdppapp.mapper;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBMAPFRECDPP0009Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBMAPFRECDPP0009.IFHMBMAPFRECDPP0009RSAServicePayload;

public interface IFHMBMAPFRECDPP0009Mapper {
    
    public int getAssetCount(IFHMBMAPFRECDPP0009Dto dto)throws Exception;

    public int getCheckoutRowId(IFHMBMAPFRECDPP0009Dto dto)throws Exception;

    public int insertObject(IFHMBMAPFRECDPP0009Dto dto)throws Exception;

    public int updateObject(IFHMBMAPFRECDPP0009Dto dto)throws Exception;

    public int rasServiceCheck(IFHMBMAPFRECDPP0009RSAServicePayload dto)throws Exception;

    public int insertResSetvice(IFHMBMAPFRECDPP0009RSAServicePayload dto)throws Exception;

    public int updateResSetvice(IFHMBMAPFRECDPP0009RSAServicePayload dto)throws Exception;
}
