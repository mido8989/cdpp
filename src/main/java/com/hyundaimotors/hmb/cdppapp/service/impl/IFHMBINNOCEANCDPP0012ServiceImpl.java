package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0012Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0012Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0022Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0012Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0012ServiceImpl implements IFHMBINNOCEANCDPP0012Service{
    
    private final IFHMBINNOCEANCDPP0012Mapper mapper;   

    public IFHMBINNOCEANCDPP0012Dto getChannelPartner(IFHMBINNOCEANCDPP0012Dto dto)throws Exception{

        return mapper.getChannelPartner(dto);
    }

    public List<IFHMBINNOCEANCDPP0012Dto> getChannelPartnerContactListList(IFHMBINNOCEANCDPP0012Dto dto)throws Exception{

        return mapper.getChannelPartnerContactListList(dto);
    }
}
