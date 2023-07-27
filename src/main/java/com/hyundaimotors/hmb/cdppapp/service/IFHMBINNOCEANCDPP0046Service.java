package com.hyundaimotors.hmb.cdppapp.service;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0032Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0046Dto;

public interface IFHMBINNOCEANCDPP0046Service {
    
    public List<IFHMBINNOCEANCDPP0046Dto> getList(IFHMBINNOCEANCDPP0046Dto dto)throws Exception;
}
