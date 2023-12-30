package com.hyundaimotors.hmb.cdppapp.service;

import org.springframework.scheduling.annotation.Async;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0034Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0035Dto;

public interface IFHMBSAPEAICDPP0035Service {

    public IFHMBSAPEAICDPP0035Dto insertObject(IFHMBSAPEAICDPP0035Dto dto)throws Exception;

    @Async
    public void insertDPObject(IFHMBSAPEAICDPP0035Dto dto)throws Exception;
    
}
