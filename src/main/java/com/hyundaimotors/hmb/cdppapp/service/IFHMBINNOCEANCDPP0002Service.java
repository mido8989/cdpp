package com.hyundaimotors.hmb.cdppapp.service;

import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowPayLoad;

public interface IFHMBINNOCEANCDPP0002Service {
    public InboundContactWorkflowPayLoad.Response insertInboundContactWorkflow(InboundContactWorkflowPayLoad.Request dto) throws Exception;
}
