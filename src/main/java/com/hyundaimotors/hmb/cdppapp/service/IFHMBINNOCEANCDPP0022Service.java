package com.hyundaimotors.hmb.cdppapp.service;

import org.springframework.scheduling.annotation.Async;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0022.IFHMBINNOCEANCDPP0022Dto;

public interface IFHMBINNOCEANCDPP0022Service {
    
    public IFHMBINNOCEANCDPP0022Dto insertObject(IFHMBINNOCEANCDPP0022Dto dto)throws Exception;

    @Async
    public void insertDPObject(IFHMBINNOCEANCDPP0022Dto dto)throws Exception;
    
}
