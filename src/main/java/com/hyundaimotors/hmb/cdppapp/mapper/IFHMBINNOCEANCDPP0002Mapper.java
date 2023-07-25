package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.s_contactDto;
import com.hyundaimotors.hmb.cdppapp.dto.s_contact_xDto;
import com.hyundaimotors.hmb.cdppapp.dto.s_contact_xmDto;

public interface IFHMBINNOCEANCDPP0002Mapper {
    
    public int InsertInboundContactWorkflow(s_contactDto sContactDto) throws Exception;

    public int InsertSContactXDto(s_contact_xDto sContactXDto) throws Exception;

    public int InsertSContactXMDtoList(List<s_contact_xmDto> sContactXmDtoList) throws Exception;
}
