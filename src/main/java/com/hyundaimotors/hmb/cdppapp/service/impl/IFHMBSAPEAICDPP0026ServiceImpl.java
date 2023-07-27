package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0026Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBSAPEAICDPP0003Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBSAPEAICDPP0026Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPEAICDPP0026Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBSAPEAICDPP0026ServiceImpl implements IFHMBSAPEAICDPP0026Service{
    
    private final IFHMBSAPEAICDPP0026Mapper mapper;

    public int insertList(List<IFHMBSAPEAICDPP0026Dto> list)throws Exception{

        return mapper.insertList(list);
    }

    public int updateList(List<IFHMBSAPEAICDPP0026Dto> list)throws Exception{

        return mapper.updateList(list);
    }
}
