package com.hyundaimotors.hmb.cdppapp.service;

import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Async;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBDMSCDPP0004.GetLeadQuExpertDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBDMSCDPP0004.IFHMBDMSCDPP0004Dto;

public interface IFHMBDMSCDPP0004Service {
    
    public IFHMBDMSCDPP0004Dto insertObject(IFHMBDMSCDPP0004Dto dto)throws Exception;

    public List<GetLeadQuExpertDto> getQuexpertList()throws ParseException;

    @Async
    public void UpdateOppt(HashMap<String, String[]> map)throws Exception;
    
}
