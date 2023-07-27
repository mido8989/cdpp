package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0012Dto;

public interface IFHMBINNOCEANCDPP0012Mapper {
    
    public IFHMBINNOCEANCDPP0012Dto getChannelPartner(IFHMBINNOCEANCDPP0012Dto dto)throws Exception;

    public List<IFHMBINNOCEANCDPP0012Dto> getChannelPartnerContactList(IFHMBINNOCEANCDPP0012Dto dto)throws Exception;
}
