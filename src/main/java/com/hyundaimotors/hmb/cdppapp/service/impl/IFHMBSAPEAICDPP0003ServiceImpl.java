package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0003Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBSAPEAICDPP0003Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPEAICDPP0003Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBSAPEAICDPP0003ServiceImpl implements IFHMBSAPEAICDPP0003Service{
    
    private final IFHMBSAPEAICDPP0003Mapper mapper;

    public IFHMBSAPEAICDPP0003Dto insertObject(IFHMBSAPEAICDPP0003Dto dto)throws Exception{

        IFHMBSAPEAICDPP0003Dto resulDto = new IFHMBSAPEAICDPP0003Dto();

        int resultNum01 = mapper.insertProd(dto);

        int resultNum02 = 0;

        if(resulDto != null){
            resultNum02 = mapper.insertProdx(dto);
        }
        
        if(resultNum02 > 0){
            resulDto.setErrorSpcCode("200");
            resulDto.setErrorSpcMessage("success");
        }


        return resulDto;
    }

    public IFHMBSAPEAICDPP0003Dto updateObject(IFHMBSAPEAICDPP0003Dto dto)throws Exception{
        
        IFHMBSAPEAICDPP0003Dto resulDto = new IFHMBSAPEAICDPP0003Dto();

        int resultNum01 = mapper.updateProd(dto);

        if(resultNum01 > 0){
            dto.setRowId(mapper.getRowIdProdx(dto));
        }

        int resultNum02 = mapper.updateProdx(dto);

        if(resultNum02 > 0){
            resulDto.setErrorSpcCode("200");
            resulDto.setErrorSpcMessage("success");
        }
        return resulDto;
    }    
}
