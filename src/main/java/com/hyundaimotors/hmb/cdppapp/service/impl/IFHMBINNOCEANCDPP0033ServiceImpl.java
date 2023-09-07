package com.hyundaimotors.hmb.cdppapp.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0033Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0033Mapper;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0033.HMBVehicle;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0033Service;


import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0033ServiceImpl implements IFHMBINNOCEANCDPP0033Service{
    private final IFHMBINNOCEANCDPP0033Mapper mapper;

    public IFHMBINNOCEANCDPP0033Dto getObject(IFHMBINNOCEANCDPP0033Dto dto)throws Exception{
        IFHMBINNOCEANCDPP0033Dto resulDto = new IFHMBINNOCEANCDPP0033Dto();
        List<HMBVehicle> vehicleList = new ArrayList<>();
        
        vehicleList = mapper.getAsset(dto);

        resulDto.setListOfHMBVehicle(vehicleList);
        return resulDto;
    }
}
