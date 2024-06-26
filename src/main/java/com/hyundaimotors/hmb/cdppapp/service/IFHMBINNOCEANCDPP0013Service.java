package com.hyundaimotors.hmb.cdppapp.service;

import java.util.HashMap;

import org.springframework.scheduling.annotation.Async;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Dto;

public interface IFHMBINNOCEANCDPP0013Service {
    public HashMap<String, IFHMBINNOCEANCDPP0013Dto> upsertObject(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    @Async
    public void insertDPObject(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;
}

