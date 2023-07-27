package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0026Dto;

public interface IFHMBSAPEAICDPP0026Mapper {
    
    public int insertList(List<IFHMBSAPEAICDPP0026Dto> list)throws Exception;

    public int updateList(List<IFHMBSAPEAICDPP0026Dto> list)throws Exception;

}
