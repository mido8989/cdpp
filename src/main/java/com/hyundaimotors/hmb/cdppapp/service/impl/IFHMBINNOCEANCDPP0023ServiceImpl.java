package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0023Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0022Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0023Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0023Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0023ServiceImpl implements IFHMBINNOCEANCDPP0023Service{
    
    private final IFHMBINNOCEANCDPP0023Mapper mapper;   

    public IFHMBINNOCEANCDPP0023Dto upsertObject(IFHMBINNOCEANCDPP0023Dto dto)throws Exception{
        int resultNum = 0;
        mapper.insertObject(dto);

        mapper.insertDay(dto);

        IFHMBINNOCEANCDPP0023Dto resulDto = new IFHMBINNOCEANCDPP0023Dto();

        return resulDto;
    }
}
