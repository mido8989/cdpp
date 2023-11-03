package com.hyundaimotors.hmb.cdppapp.controller.voc;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPCDPP0031.IFHMBSAPCDPP0031Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPCDPP0031.HMBSearchServiceRequestTDWebserviceOutput;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPCDPP0031.IFHMBSAPCDPP0031Payload;
import com.hyundaimotors.hmb.cdppapp.service.ApiLogService;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPCDPP0031Service;
import com.hyundaimotors.hmb.cdppapp.util.ApiLog;
import com.hyundaimotors.hmb.cdppapp.util.ApiLogStep;
import com.hyundaimotors.hmb.cdppapp.util.JsonUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "HMB Search Service Request TD Webservice WF", description = "This web service will be created to be used to search Service Request  by Date Input and return  protocol information.")
@RestController
@RequiredArgsConstructor
public class IFHMBSAPCDPP0031Controller {

    private static final String IF_ID = "IF034";
    
    private final ApiLogService logService;
    
    private final IFHMBSAPCDPP0031Service service;

    private final ModelMapper defaultMapper;

    @Operation(summary = "HMB Search Service Request TD Webservice WF", description = "HMB Search Service Request TD Webservice WF")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBSAPCDPP0031Payload.Request.class)))
    @PostMapping(value = "/api/v1/HMBSearchServiceRequestTDWebserviceWF")
    public Object getObject(@Valid @RequestBody IFHMBSAPCDPP0031Payload.Request request) throws Exception {
        UUID IF_TR_ID = UUID.randomUUID();
        
        IFHMBSAPCDPP0031Payload.Response response = new IFHMBSAPCDPP0031Payload.Response();
        ApiLog.logApi(logService, IF_ID, ApiLogStep.START, IF_TR_ID, JsonUtils.toJson(request));
        
        try {
            IFHMBSAPCDPP0031Dto dto = defaultMapper.map(request, IFHMBSAPCDPP0031Dto.class);
            
            ApiLog.logApi(logService, IF_ID,ApiLogStep.STEP1, IF_TR_ID, null);
            IFHMBSAPCDPP0031Dto resultDto = service.getObject(dto);
            ApiLog.logApi(logService, IF_ID,ApiLogStep.STEP2, IF_TR_ID, null);
    
            response = defaultMapper.map(resultDto, IFHMBSAPCDPP0031Payload.Response.class);
            ApiLog.logApi(logService, IF_ID,ApiLogStep.FINISH, IF_TR_ID, JsonUtils.toJson(response));
        }catch(Exception e) {
            response.getHmbSearchServiceRequestTDWebserviceOutput().setErrorSpcCode("500");
            response.getHmbSearchServiceRequestTDWebserviceOutput().setErrorSpcMessage(e.getLocalizedMessage());
            ApiLog.logApi(logService, IF_ID,ApiLogStep.FINISH, IF_TR_ID, JsonUtils.toJson(response), e);
        }
        return response;
    }
}
