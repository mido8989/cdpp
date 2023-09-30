package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBCSUCDPP0085.ChannelPartnerCSUDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBCSUCDPP0085.IFHMBCSUCDPP0085Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBCSUCDPP0085Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBCSUCDPP0085Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBCSUCDPP0085ServiceImpl implements IFHMBCSUCDPP0085Service{

    private final IFHMBCSUCDPP0085Mapper mapper;
    
    public IFHMBCSUCDPP0085Dto insertObject(IFHMBCSUCDPP0085Dto dto)throws Exception{

        IFHMBCSUCDPP0085Dto resulDto = new IFHMBCSUCDPP0085Dto();

        List<ChannelPartnerCSUDto> channelPartnerList = new ArrayList<>();

        channelPartnerList = mapper.getChannelObject(dto);

        if(0 < channelPartnerList.size()){
            resulDto.setChannelPartner(channelPartnerList);

            resulDto.setErrorSpcCode("0");
            resulDto.setErrorSpcMessage("OK");
        }else{
            resulDto.setErrorSpcCode("1");
            resulDto.setErrorSpcMessage("Fail");
        }
        
        return resulDto;
    }
}
