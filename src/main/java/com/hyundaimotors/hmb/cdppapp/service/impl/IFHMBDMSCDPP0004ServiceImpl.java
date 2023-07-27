package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBDMSCDPP0004Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBDMSCDPP0004Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBDMSCDPP0004Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBDMSCDPP0004ServiceImpl implements IFHMBDMSCDPP0004Service{
    
    private final IFHMBDMSCDPP0004Mapper mapper;

    public List<IFHMBDMSCDPP0004Dto> getContact(IFHMBDMSCDPP0004Dto dto)throws Exception{   

        return mapper.getContact(dto);
    }

    public IFHMBDMSCDPP0004Dto contactInfoList(IFHMBDMSCDPP0004Dto dto)throws Exception{

        return mapper.contactInfoList(dto);
    }

    public List<IFHMBDMSCDPP0004Dto> veiculoInfoList(IFHMBDMSCDPP0004Dto dto)throws Exception{
        
        return mapper.veiculoInfoList(dto);
    }

    public IFHMBDMSCDPP0004Dto opvInfoList(IFHMBDMSCDPP0004Dto dto)throws Exception{

        return mapper.opvInfoList(dto);
    }
    
    public IFHMBDMSCDPP0004Dto financiamentoopvList(IFHMBDMSCDPP0004Dto dto)throws Exception{

        return mapper.financiamentoopvList(dto);
    }
}
