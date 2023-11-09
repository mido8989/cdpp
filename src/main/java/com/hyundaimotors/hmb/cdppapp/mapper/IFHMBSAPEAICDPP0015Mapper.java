package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;
import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0015.Color;

public interface IFHMBSAPEAICDPP0015Mapper {
    public void insertObject(Color dto)throws Exception;

    public void transferProcess(HashMap<String, String[]> parMap)throws Exception;

    public List<String> getProcessRowIds(List<String> paramList)throws Exception;

    public void transferReplica(HashMap<String, String[]> parMap)throws Exception;
}
