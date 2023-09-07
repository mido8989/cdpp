package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0015Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0035Dto;

public interface IFHMBSAPEAICDPP0015Mapper {
    public void insertObject(IFHMBSAPEAICDPP0015Dto dto)throws Exception;

    public void transferProcess(HashMap<String, String> parMap)throws Exception;
}
