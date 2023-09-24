package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0032.IFHMBINNOCEANCDPP0032Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0032.SearchContactTDDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0032.ServiceRequestTDDto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0032Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0032Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0032ServiceImpl  implements IFHMBINNOCEANCDPP0032Service{
    
    private final IFHMBINNOCEANCDPP0032Mapper mapper;

    public IFHMBINNOCEANCDPP0032Dto getObject(IFHMBINNOCEANCDPP0032Dto dto)throws Exception{
        IFHMBINNOCEANCDPP0032Dto resultDto = new IFHMBINNOCEANCDPP0032Dto();

        List<SearchContactTDDto> searchTD = new ArrayList<>();
        List<ServiceRequestTDDto> searchSeviceRequestTD = new ArrayList<>();

        searchTD = mapper.getSearchContact(dto);

        if(0 < searchTD.size()){
            resultDto.setListOfSearchContactTD(searchTD);

            searchSeviceRequestTD = mapper.getServiceRequest(dto);
            if(0 < searchSeviceRequestTD.size()){
                resultDto.setListOfServiceRequest(searchSeviceRequestTD);
            }
            
            resultDto.setErrorSpcCode("0");
            resultDto.setErrorSpcMessage("OK");
        }else{
            resultDto.setErrorSpcCode("1");
            resultDto.setErrorSpcMessage("no data");
        }
        
         return resultDto;
    }
}
