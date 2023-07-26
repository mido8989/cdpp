package com.hyundaimotors.hmb.cdppapp.service;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBDMSCDPP0004Dto;

public interface IFHMBDMSCDPP0004Service {
    
    public List<IFHMBDMSCDPP0004Dto> getContact(IFHMBDMSCDPP0004Dto dto)throws Exception;

    public IFHMBDMSCDPP0004Dto contactInfoList(IFHMBDMSCDPP0004Dto dto)throws Exception;

    public List<IFHMBDMSCDPP0004Dto> veiculoInfoList(IFHMBDMSCDPP0004Dto dto)throws Exception;

    public IFHMBDMSCDPP0004Dto opvInfoList(IFHMBDMSCDPP0004Dto dto)throws Exception;

    public IFHMBDMSCDPP0004Dto financiamentoopvList(IFHMBDMSCDPP0004Dto dto)throws Exception;
    
}
