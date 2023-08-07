package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBMAPFRECDPP0009Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0046Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBMAPFRECDPP0009Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBMAPFRECDPP0009Service;

import jakarta.validation.OverridesAttribute.List;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBMAPFRECDPP0009ServiceImpl implements IFHMBMAPFRECDPP0009Service{
    
    private final IFHMBMAPFRECDPP0009Mapper mapper;

    public IFHMBMAPFRECDPP0009Dto upsertObject(IFHMBMAPFRECDPP0009Dto dto)throws Exception{
        IFHMBMAPFRECDPP0009Dto resultDto = new IFHMBMAPFRECDPP0009Dto();

        int rowIdCount = mapper.getCheckoutRowId(dto);

        /*if(0 < rowIdCount){
            int resultNum = mapper.updateObject(dto);
        }else{
            int resultNum = mapper.insertObject(dto);
        }*/
        return resultDto;
    }
}
