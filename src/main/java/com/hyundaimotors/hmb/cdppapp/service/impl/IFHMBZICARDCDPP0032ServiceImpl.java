package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0032Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBZICARDCDPP0001Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBZICARDCDPP0032Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBZICARDCDPP0032Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IFHMBZICARDCDPP0032ServiceImpl  implements IFHMBZICARDCDPP0032Service{
    
    private final IFHMBZICARDCDPP0032Mapper mapper;

    public IFHMBZICARDCDPP0032Dto getSearchContact(IFHMBZICARDCDPP0032Dto dto)throws Exception{

        return mapper.getSearchContact(dto);
    }

    public List<IFHMBZICARDCDPP0032Dto> getlistOfServiceRequest(IFHMBZICARDCDPP0032Dto dto)throws Exception{

        return mapper.getlistOfServiceRequest(dto);
    }
}
