package com.hyundaimotors.hmb.cdppapp.controller.foundation;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPCDPP0005.IFHMBSAPCDPP0005Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPCDPP0005Payload;
import com.hyundaimotors.hmb.cdppapp.service.ApiLogService;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPCDPP0005Service;
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

@Tag(name = "HMB Account Webservice WF", description = "SAP, EAI Create/Update Interface")
@RestController
@RequiredArgsConstructor
public class IFHMBSAPCDPP0005Controller {

    private static final String IF_ID = "IF007";
    
    private final ApiLogService logService;

    @Autowired
    private final IFHMBSAPCDPP0005Service service;

    private final ModelMapper defaultMapper;
    
    @Operation(summary = "Account upsert", description = "Account upsert")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBSAPCDPP0005Payload.Response.class)))
    @PostMapping(value = "/api/v1/HMBAccountWebserviceWF")
    public Object upsertObject(@Valid @RequestBody IFHMBSAPCDPP0005Payload.Request request, @RequestHeader MultiValueMap<String, String> headerMap) throws Exception {
        request.setApiKey(String.valueOf(headerMap.get("apikey")));
        UUID IF_TR_ID = UUID.randomUUID();

        ApiLog.logApi(logService, IF_ID, ApiLogStep.START, IF_TR_ID, JsonUtils.toJson(request));
        IFHMBSAPCDPP0005Payload.Response response = new IFHMBSAPCDPP0005Payload.Response();
        
        try {
            IFHMBSAPCDPP0005Dto dto = defaultMapper.map(request, IFHMBSAPCDPP0005Dto.class);
    
            ApiLog.logApi(logService, IF_ID,ApiLogStep.STEP1, IF_TR_ID, null);
            HashMap<String, IFHMBSAPCDPP0005Dto> resultMap = service.insertObject(dto);

            IFHMBSAPCDPP0005Dto resultDto = resultMap.get("resultDto");
            
            ApiLog.logApi(logService, IF_ID,ApiLogStep.STEP2, IF_TR_ID, null);
    
            response = ObjectUtils.isNotEmpty(resultDto) ? defaultMapper.map(resultDto, IFHMBSAPCDPP0005Payload.Response.class) : null;
            ApiLog.logApi(logService, IF_ID,ApiLogStep.FINISH, IF_TR_ID, JsonUtils.toJson(response));

            IFHMBSAPCDPP0005Dto oldAccount = resultMap.get("oldAccount");

            if(oldAccount != null){
                oldAccount.setApiKey(request.getApiKey());
                System.out.println(":::::::::: ApiKey :::::::::" + request.getApiKey());
                service.insertDPObject(oldAccount);
            }else{
                resultDto.setApiKey(request.getApiKey());
                service.insertDPObject(resultDto);
            }

        }catch(Exception e) {
            response.setErrorSpcCode("500");
            response.setErrorSpcMessage(e.getLocalizedMessage());
            ApiLog.logApi(logService, IF_ID,ApiLogStep.FINISH, IF_TR_ID, JsonUtils.toJson(response), e);
        }
        
        return response;
    }
}
