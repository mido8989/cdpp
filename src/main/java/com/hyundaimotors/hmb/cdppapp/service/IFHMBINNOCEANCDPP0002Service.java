package com.hyundaimotors.hmb.cdppapp.service;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002Dto;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowPayLoad;

public interface IFHMBINNOCEANCDPP0002Service {
    public IFHMBINNOCEANCDPP0002Dto insertObject(IFHMBINNOCEANCDPP0002Dto dto)throws Exception;
}
