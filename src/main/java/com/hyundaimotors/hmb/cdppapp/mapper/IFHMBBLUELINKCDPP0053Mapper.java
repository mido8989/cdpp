package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;
import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBBLUELINKCDPP0053.BluelinkDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBBLUELINKCDPP0053.VehicleBluelinkDto;

public interface IFHMBBLUELINKCDPP0053Mapper {
    
    public void inserBluelink(BluelinkDto bluelinkDto)throws Exception;

    public void transferProcess(HashMap<String, String[]> parMap)throws Exception;

    //public List<String> getProcessRowIds(List<String> paramList)throws Exception;
}
