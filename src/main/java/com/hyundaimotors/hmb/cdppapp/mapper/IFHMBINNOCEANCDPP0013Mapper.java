package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013.ListOfContactsDto;

public interface IFHMBINNOCEANCDPP0013Mapper {
    
    public void insertBusinessAccount(IFHMBINNOCEANCDPP0013Dto dto)throws Exception;

    public void insertPersonAccount(ListOfContactsDto dto)throws Exception;

    public void transferProcess(HashMap<String, String[]> parMap)throws Exception;

    public void transferReplica(HashMap<String, String[]> parMap)throws Exception;

    public void transferProcessContact(HashMap<String, String> parMap)throws Exception;
    
    /**
     * 
     */
    public String getProcessAccountRowId(IFHMBINNOCEANCDPP0013Dto dto) throws Exception;
    public String getProcessContactRowId(ListOfContactsDto dto) throws Exception;
    public String getReplicaAccountId(IFHMBINNOCEANCDPP0013Dto dto) throws Exception;
    public String getReplicaContactId(ListOfContactsDto dto) throws Exception;
}
