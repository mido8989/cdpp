package com.hyundaimotors.hmb.cdppapp.service;

import java.util.HashMap;

import org.springframework.scheduling.annotation.Async;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002.IFHMBINNOCEANCDPP0002Dto;

public interface IFHMBINNOCEANCDPP0002Service {
    
    public HashMap<String, IFHMBINNOCEANCDPP0002Dto> insertObject(IFHMBINNOCEANCDPP0002Dto dto)throws Exception;

    @Async
    public void insertDPObject(IFHMBINNOCEANCDPP0002Dto dto)throws Exception;

}
