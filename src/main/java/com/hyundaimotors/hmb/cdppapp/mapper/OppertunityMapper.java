package com.hyundaimotors.hmb.cdppapp.mapper;

import com.hyundaimotors.hmb.cdppapp.dto.Oppertunity.OppertunityDto;

public interface OppertunityMapper {
    
    public void insertObject(OppertunityDto dto)throws Exception;

    public void updateObject(OppertunityDto dto)throws Exception;
}
