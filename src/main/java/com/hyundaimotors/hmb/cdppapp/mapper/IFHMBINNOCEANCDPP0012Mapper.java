package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0012.ChannelPartnerContactDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0012.ChannelPartnerDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0012.IFHMBINNOCEANCDPP0012Dto;

public interface IFHMBINNOCEANCDPP0012Mapper {
    
    public List<ChannelPartnerDto> getChannelPartner(IFHMBINNOCEANCDPP0012Dto dto)throws Exception;

    public List<ChannelPartnerContactDto> getChannelPartnerContactList(IFHMBINNOCEANCDPP0012Dto dto)throws Exception;
}
