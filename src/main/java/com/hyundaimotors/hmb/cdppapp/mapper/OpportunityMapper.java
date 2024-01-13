package com.hyundaimotors.hmb.cdppapp.mapper;

import com.hyundaimotors.hmb.cdppapp.dto.OpportunityReverse.OpportunityDto;

public interface OpportunityMapper {

    public int getRowId(OpportunityDto dto)throws Exception;
    
    public void insertObject(OpportunityDto dto)throws Exception;

    public void updateObject(OpportunityDto dto)throws Exception;
}
