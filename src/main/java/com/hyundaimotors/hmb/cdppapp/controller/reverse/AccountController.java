package com.hyundaimotors.hmb.cdppapp.controller.reverse;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.payload.AccountPayLoad;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBZICARDCDPP0007Payload;
import com.hyundaimotors.hmb.cdppapp.payload.AccountReverse.AccountReversePayLoad;
import com.hyundaimotors.hmb.cdppapp.payload.OpportunityReverse.OpportunityPayload;
import com.hyundaimotors.hmb.cdppapp.service.AccountService;
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
    
    private static final String IF_ID = "IF098";
    
    private final ApiLogService logService;

    @Autowired
    private final AccountService service;
    
    private final ModelMapper defaultMapper;

    @Operation(summary = "Account Reverse I/F", description = "Account Reverse I/F")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBZICARDCDPP0007Payload.Response.class)))
    @PostMapping(value = "/api/v1/AccountReverseIF")
    public Object getObject(@Valid @RequestBody AccountReversePayLoad.Request request) throws Exception {
        UUID IF_TR_ID = UUID.randomUUID();

        AccountReversePayLoad.Response response = new AccountReversePayLoad.Response();
        ApiLog.logApi(logService, IF_ID, ApiLogStep.START, IF_TR_ID, JsonUtils.toJson(request));

        try {
            
        }catch(Exception e) {
            response.setErrorSpcCode("500");
            response.setErrorSpcMessage(e.getLocalizedMessage());
            ApiLog.logApi(logService, IF_ID,ApiLogStep.FINISH, IF_TR_ID, JsonUtils.toJson(response), e);
       }
        return "";
    }
}
