package com.hyundaimotors.hmb.cdppapp.controller.reverse;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0015.IFHMBSAPEAICDPP0015Dto;
import com.hyundaimotors.hmb.cdppapp.dto.Oppertunity.OppertunityDto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBZICARDCDPP0007Payload;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0015.IFHMBSAPEAICDPP0015Payload;
import com.hyundaimotors.hmb.cdppapp.payload.Oppertunity.OppertunityPayload;
import com.hyundaimotors.hmb.cdppapp.service.ApiLogService;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBZICARDCDPP0007Service;
import com.hyundaimotors.hmb.cdppapp.service.OppertunityService;
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
public class OppertunityController {
    private static final String IF_ID = "IF098";
    
    private final ApiLogService logService;

    @Autowired
    private final OppertunityService service;
    
    private final ModelMapper defaultMapper;

    @Operation(summary = "Opportunity Reverse I/F", description = "Opportunity Reverse I/F")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBZICARDCDPP0007Payload.Response.class)))
    @PostMapping(value = "/api/v1/OpportunityReverseIF")
    public Object getObject(@Valid @RequestBody OppertunityPayload.Request request) throws Exception {
        UUID IF_TR_ID = UUID.randomUUID();
        OppertunityPayload.Response response = new OppertunityPayload.Response();
        ApiLog.logApi(logService, IF_ID, ApiLogStep.START, IF_TR_ID, JsonUtils.toJson(request));
        try {
            OppertunityDto dto = defaultMapper.map(request, OppertunityDto.class);
            OppertunityDto resultDto = new OppertunityDto();
    
            ApiLog.logApi(logService, IF_ID,ApiLogStep.STEP1, IF_TR_ID, null);
            resultDto = service.insertObject(dto);
            ApiLog.logApi(logService, IF_ID,ApiLogStep.STEP2, IF_TR_ID, null);
    
            response = defaultMapper.map(resultDto, OppertunityPayload.Response.class);
            response.setErrorSpcCode("0");
            response.setErrorSpcMessage("OK");

            ApiLog.logApi(logService, IF_ID,ApiLogStep.FINISH, IF_TR_ID, JsonUtils.toJson(response));

        }catch(Exception e) {
             response.setErrorSpcCode("500");
             response.setErrorSpcMessage(e.getLocalizedMessage());
             ApiLog.logApi(logService, IF_ID,ApiLogStep.FINISH, IF_TR_ID, JsonUtils.toJson(response), e);
        }
        
        return response;
    }
}
