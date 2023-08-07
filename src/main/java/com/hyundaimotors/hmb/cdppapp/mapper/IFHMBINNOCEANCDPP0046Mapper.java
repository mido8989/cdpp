package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;
import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0046Dto;

public interface IFHMBINNOCEANCDPP0046Mapper {

    public String getParRowId(IFHMBINNOCEANCDPP0046Dto dto)throws Exception;

    public String getReplicaRowId(IFHMBINNOCEANCDPP0046Dto dto)throws Exception;

    public int deleteList(IFHMBINNOCEANCDPP0046Dto dto)throws Exception;

    public int insertList(IFHMBINNOCEANCDPP0046Dto dto)throws Exception;

    public void transferProcess(HashMap<String, String> parMap)throws Exception;
                
    public void transferReplica(HashMap<String, String> replicaMap)throws Exception;
}
