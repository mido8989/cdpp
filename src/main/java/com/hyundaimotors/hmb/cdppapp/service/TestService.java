package com.hyundaimotors.hmb.cdppapp.service;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.AccountDto;
import com.hyundaimotors.hmb.cdppapp.dto.TestDto;
import com.hyundaimotors.hmb.cdppapp.dto.s_contactDto;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowPayLoad;

public interface TestService {

    public TestDto insertList(TestDto dto) throws Exception;

    public TestDto getList(TestDto dto) throws Exception;

    public AccountDto getAccountList(AccountDto dto) throws Exception;

    public InboundContactWorkflowPayLoad.Response upsertInboundContactWorkflow(InboundContactWorkflowPayLoad.Request dto) throws Exception;
}
