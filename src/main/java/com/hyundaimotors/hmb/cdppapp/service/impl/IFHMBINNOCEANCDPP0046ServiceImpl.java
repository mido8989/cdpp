package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0046Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0046Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0046Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0046ServiceImpl implements IFHMBINNOCEANCDPP0046Service{
    
    private final IFHMBINNOCEANCDPP0046Mapper mapper;

    public List<IFHMBINNOCEANCDPP0046Dto> getList(IFHMBINNOCEANCDPP0046Dto dto)throws Exception{
        List<IFHMBINNOCEANCDPP0046Dto> list = mapper.getList(dto);

        if(list.size() > 0){
            for(int i=0; i < list.size();i++){
                list.get(i).setErrorSpcCode("0");
                list.get(i).setErrorSpcMessage("OK");
            }
        }

        return list;
    }
}
