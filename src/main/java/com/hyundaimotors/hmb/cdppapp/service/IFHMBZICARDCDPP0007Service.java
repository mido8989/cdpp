package com.hyundaimotors.hmb.cdppapp.service;

import org.springframework.scheduling.annotation.Async;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0007Dto;

public interface IFHMBZICARDCDPP0007Service {

    public  IFHMBZICARDCDPP0007Dto upsertObject(IFHMBZICARDCDPP0007Dto dto)throws Exception;

    @Async
    public void insertDPObject(IFHMBZICARDCDPP0007Dto dto)throws Exception;
    
}
