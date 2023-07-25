package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0032Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0032Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0032Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0032ServiceImpl  implements IFHMBINNOCEANCDPP0032Service{
    
    private final IFHMBINNOCEANCDPP0032Mapper mapper;

    public IFHMBINNOCEANCDPP0032Dto getSearchContact(IFHMBINNOCEANCDPP0032Dto dto)throws Exception{

        return mapper.getSearchContact(dto);
    }

    public List<IFHMBINNOCEANCDPP0032Dto> getlistOfServiceRequest(IFHMBINNOCEANCDPP0032Dto dto)throws Exception{

        return mapper.getlistOfServiceRequest(dto);
    }
}
