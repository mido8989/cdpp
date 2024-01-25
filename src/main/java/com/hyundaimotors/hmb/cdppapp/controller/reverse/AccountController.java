package com.hyundaimotors.hmb.cdppapp.controller.reverse;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.AccountReverse.AccountReverseDto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBZICARDCDPP0007Payload;
import com.hyundaimotors.hmb.cdppapp.payload.AccountReverse.AccountReversePayLoad;
import com.hyundaimotors.hmb.cdppapp.payload.OpportunityReverse.OpportunityPayload;
import com.hyundaimotors.hmb.cdppapp.service.AccountReverseService;
import com.hyundaimotors.hmb.cdppapp.service.ApiLogService;
import com.hyundaimotors.hmb.cdppapp.util.ApiLog;
import com.hyundaimotors.hmb.cdppapp.util.ApiLogStep;
import com.hyundaimotors.hmb.cdppapp.util.JsonUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountController {
    
    private static final String IF_ID = "IF099";
    
    private final ApiLogService logService;

    @Autowired
    private final AccountReverseService service;
    
    private final ModelMapper defaultMapper;

    @Operation(summary = "Account Reverse I/F", description = "Account Reverse I/F")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBZICARDCDPP0007Payload.Response.class)))
    @PostMapping(value = "/api/v1/AccountReverseIF")
    public Object getObject(@Valid @RequestBody AccountReversePayLoad.Request request) throws Exception {
        UUID IF_TR_ID = UUID.randomUUID();

        AccountReversePayLoad.Response response = new AccountReversePayLoad.Response();
        ApiLog.logApi(logService, IF_ID, ApiLogStep.START, IF_TR_ID, JsonUtils.toJson(request));

        try {
            AccountReverseDto dto = defaultMapper.map(request, AccountReverseDto.class);
            AccountReverseDto resultDto = new AccountReverseDto();

            ApiLog.logApi(logService, IF_ID,ApiLogStep.STEP1, IF_TR_ID, null);
            resultDto = service.insertObject(dto);
            ApiLog.logApi(logService, IF_ID,ApiLogStep.STEP2, IF_TR_ID, null);
    
            response = defaultMapper.map(resultDto, AccountReversePayLoad.Response.class);
            response.setErrorSpcCode("0");
            response.setErrorSpcMessage("OK");

            ApiLog.logApi(logService, IF_ID,ApiLogStep.FINISH, IF_TR_ID, JsonUtils.toJson(response));

            ApiLog.logApi(logService, IF_ID,ApiLogStep.STEP1, IF_TR_ID, null);
        }catch(Exception e) {
            response.setErrorSpcCode("500");
            response.setErrorSpcMessage(e.getLocalizedMessage());
            ApiLog.logApi(logService, IF_ID,ApiLogStep.FINISH, IF_TR_ID, JsonUtils.toJson(response), e);
       }
       return response;
    }
}
