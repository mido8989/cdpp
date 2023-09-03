package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0034Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSMARTERSCDPP0036Dto;

public interface IFHMBSMARTERSCDPP0036Mapper {
    
    public void insertServiceRequestChatInput(IFHMBSMARTERSCDPP0036Dto dto)throws Exception;

    public void insertChat(IFHMBSMARTERSCDPP0036Dto dto)throws Exception;

    public void insertMessage(IFHMBSMARTERSCDPP0036Dto dto)throws Exception;

    public void transferProcess(HashMap<String, String> parMap)throws Exception;

    public void transferReplica(HashMap<String, String> parMap)throws Exception;
}
