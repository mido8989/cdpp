package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;
import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002Dto;
import com.hyundaimotors.hmb.cdppapp.dto.s_contactDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002Car;
import com.hyundaimotors.hmb.cdppapp.dto.s_contact_xmDto;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowCarPayLoad;

public interface IFHMBINNOCEANCDPP0002Mapper {
    
    public int InsertInboundContactWorkflow(IFHMBINNOCEANCDPP0002Dto dto) throws Exception;

    public int InsertSContactXDto(IFHMBINNOCEANCDPP0002Dto dto) throws Exception;

    public int InsertSContactXMDtoList(List<InboundContactWorkflowCarPayLoad> car) throws Exception;

    public void transferProcess(HashMap<String, String> parMap)throws Exception;

    public void transferReplica(HashMap<String, String> parMap)throws Exception;
}
