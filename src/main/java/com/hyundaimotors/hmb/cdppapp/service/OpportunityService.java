package com.hyundaimotors.hmb.cdppapp.service;

import com.hyundaimotors.hmb.cdppapp.dto.OpportunityReverse.OpportunityDto;

public interface OpportunityService {
    
    public OpportunityDto insertObject(OpportunityDto dto)throws Exception;
}
