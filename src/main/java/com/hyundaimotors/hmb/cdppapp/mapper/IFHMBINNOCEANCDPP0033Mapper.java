package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0033Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0033.HMBVehicle;

public interface IFHMBINNOCEANCDPP0033Mapper {
    public List<HMBVehicle> getAsset(IFHMBINNOCEANCDPP0033Dto dto)throws Exception;
}
