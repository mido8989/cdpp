package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;
import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0026Dto;

public interface IFHMBSAPEAICDPP0026Mapper {
    
    public int insertList(List<IFHMBSAPEAICDPP0026Dto> list)throws Exception;

    public int updateList(List<IFHMBSAPEAICDPP0026Dto> list)throws Exception;

    public void transferProcess(HashMap<String, String> parMap)throws Exception;

    public String processPoductId(String externalId)throws Exception;

    public void transferReplica(HashMap<String, String> parMap)throws Exception;

}
