package com.hyundaimotors.hmb.cdppapp.service;

import org.springframework.scheduling.annotation.Async;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0046.IFHMBINNOCEANCDPP0046Dto;

public interface IFHMBINNOCEANCDPP0046Service {
    
    public IFHMBINNOCEANCDPP0046Dto insertList(IFHMBINNOCEANCDPP0046Dto dto)throws Exception;
    
    @Async
    public void insertDPObject(IFHMBINNOCEANCDPP0046Dto dto)throws Exception;
}

