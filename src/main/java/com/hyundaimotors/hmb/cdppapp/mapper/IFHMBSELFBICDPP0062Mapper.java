package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSELFBICDPP0062.IFHMBSELFBICDPP0062Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSELFBICDPP0062.TotalActionDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSELFBICDPP0062.TotalContactDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSELFBICDPP0062.TotalLeadDto;

public interface IFHMBSELFBICDPP0062Mapper {
    
    public TotalLeadDto getLead(IFHMBSELFBICDPP0062Dto dto)throws Exception;

    public List<TotalContactDto> getContact(String contactRowId)throws Exception;

    public TotalActionDto getAction(String protocol)throws Exception;
}