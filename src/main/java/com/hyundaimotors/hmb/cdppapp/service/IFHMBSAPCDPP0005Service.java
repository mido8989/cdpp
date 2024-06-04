package com.hyundaimotors.hmb.cdppapp.service;

import java.util.HashMap;

import org.springframework.scheduling.annotation.Async;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPCDPP0005.IFHMBSAPCDPP0005Dto;

public interface IFHMBSAPCDPP0005Service {
    
    public HashMap<String, IFHMBSAPCDPP0005Dto> insertObject(IFHMBSAPCDPP0005Dto dto)throws Exception;
    
    @Async
    public void insertDPObject(IFHMBSAPCDPP0005Dto dto)throws Exception;
}
