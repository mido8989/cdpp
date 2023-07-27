package com.hyundaimotors.hmb.cdppapp.service;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0012Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0032Dto;

public interface IFHMBINNOCEANCDPP0012Service {
    
    public IFHMBINNOCEANCDPP0012Dto getChannelPartner(IFHMBINNOCEANCDPP0012Dto dto)throws Exception;

    public List<IFHMBINNOCEANCDPP0012Dto> getChannelPartnerContactList(IFHMBINNOCEANCDPP0012Dto dto)throws Exception;
}
