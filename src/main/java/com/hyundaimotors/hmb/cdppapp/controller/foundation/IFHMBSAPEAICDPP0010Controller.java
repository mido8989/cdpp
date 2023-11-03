package com.hyundaimotors.hmb.cdppapp.controller.foundation;

import java.util.HashMap;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0010.IFHMBSAPEAICDPP0010Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0010Payload;
import com.hyundaimotors.hmb.cdppapp.service.ApiLogService;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPEAICDPP0010Service;
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

@Tag(name = "HMB Auto Vehicle Webservice WF", description = "UpsertAutoVehicle")
@RestController
@RequiredArgsConstructor
public class IFHMBSAPEAICDPP0010Controller {

    private static final String IF_ID = "IF011";
    
    private final ApiLogService logService;

    @Autowired
    private final IFHMBSAPEAICDPP0010Service service;

    @Operation(summary = "asset upsert", description = "asset upsert")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBSAPEAICDPP0010Payload.Response.class)))
    @PostMapping(value = "/api/v1/HMBAutoVehicleUpsertWS")
    public Object upserObject(@Valid @RequestBody IFHMBSAPEAICDPP0010Payload.Request request) throws Exception {
        UUID IF_TR_ID = UUID.randomUUID();
        
        IFHMBSAPEAICDPP0010Payload.Response response = new IFHMBSAPEAICDPP0010Payload.Response();
        ApiLog.logApi(logService, IF_ID, ApiLogStep.START, IF_TR_ID, JsonUtils.toJson(request));
        
        ModelMapper modelMapper = new ModelMapper();
        try {
            IFHMBSAPEAICDPP0010Dto dto = modelMapper.map(request, IFHMBSAPEAICDPP0010Dto.class);
    
            ApiLog.logApi(logService, IF_ID,ApiLogStep.STEP1, IF_TR_ID, null);
            HashMap<String, Object> result = service.UpsertAutoVehicle(dto);
            ApiLog.logApi(logService, IF_ID,ApiLogStep.STEP2, IF_TR_ID, null);
    
            response = modelMapper.map(result, IFHMBSAPEAICDPP0010Payload.Response.class);
            ApiLog.logApi(logService, IF_ID,ApiLogStep.FINISH, IF_TR_ID, JsonUtils.toJson(response));
        }catch(Exception e) {
            response.setErrorSpcCode("500");
            response.setErrorSpcMessage(e.getLocalizedMessage());
            ApiLog.logApi(logService, IF_ID,ApiLogStep.FINISH, IF_TR_ID, JsonUtils.toJson(response), e);
        }
        return response;
    }
}
