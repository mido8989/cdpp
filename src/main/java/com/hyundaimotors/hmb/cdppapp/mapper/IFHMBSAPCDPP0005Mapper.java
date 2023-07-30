package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPCDPP0005Dto;

public interface IFHMBSAPCDPP0005Mapper {
    
    public int insertSoe(IFHMBSAPCDPP0005Dto dto)throws Exception;

    public int insertSoex(IFHMBSAPCDPP0005Dto dto)throws Exception;

    public int insertSapr(IFHMBSAPCDPP0005Dto dto)throws Exception;

    public void insertAccount(HashMap<String, String> parMap)throws Exception;

    public int updateSoe(IFHMBSAPCDPP0005Dto dto)throws Exception;

    public int updateSoex(IFHMBSAPCDPP0005Dto dto)throws Exception;

    public int updateSapr(IFHMBSAPCDPP0005Dto dto)throws Exception;

    public void updateAccount(HashMap<String, String> parMap)throws Exception;

}
