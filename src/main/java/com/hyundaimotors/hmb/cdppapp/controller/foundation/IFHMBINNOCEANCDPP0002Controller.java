package com.hyundaimotors.hmb.cdppapp.controller.foundation;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002.IFHMBINNOCEANCDPP0002Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0002Payload;
import com.hyundaimotors.hmb.cdppapp.service.ApiLogService;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0002Service;
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

@Tag(name = "HMB Inbound Contact Interface Workflow", description = "INNOCEAN, SAP EAI insert Interface")
@RestController
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0002Controller {

    private ThreadLocal<Object> prevReqObj = new ThreadLocal<>();

    private static final String IF_ID = "IF003";

    private final ApiLogService logService;

    private final IFHMBINNOCEANCDPP0002Service service;

    private final ModelMapper defaultMapper;

    @Operation(summary = "Person Account Insert", description = "Person Account Insert.")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBINNOCEANCDPP0002Payload.Request.class)))
    @PostMapping(value = "/api/v1/HMBInboundContactInterfaceWorkflow")
    public Object insertInboundContactWorkflow(@Valid @RequestBody IFHMBINNOCEANCDPP0002Payload.Request request, @RequestHeader MultiValueMap<String, String> headerMap) throws Exception {
        
        if( headerMap.getFirst("apikey") != null ){
            request.setApiKey(String.valueOf(headerMap.getFirst("apikey")));
        }

        UUID IF_TR_ID = UUID.randomUUID();

        ApiLog.logApi(logService, IF_ID, ApiLogStep.START, IF_TR_ID, JsonUtils.toJson(request));
        IFHMBINNOCEANCDPP0002Payload.Response response = new IFHMBINNOCEANCDPP0002Payload.Response();
        try {
            IFHMBINNOCEANCDPP0002Dto dto = defaultMapper.map(request, IFHMBINNOCEANCDPP0002Dto.class);

            System.out.println("::::::::: JsonUtils.toJson(dto) :::::::::" + JsonUtils.toJson(dto));
            System.out.println("::::::::: JsonUtils.toJson(prevReqObj.get()) :::::::::" + JsonUtils.toJson(prevReqObj.get()));
            
            // if (dto.equals(prevReqObj.get())) {
            if (!ObjectUtils.notEqual(JsonUtils.toJson(dto), JsonUtils.toJson(prevReqObj.get()))) {
                throw new IllegalArgumentException("Duplicate Request");
            }
            prevReqObj.set(JsonUtils.toJson(dto));

            // Dto Validation
            String msg = this.isValidRequest(dto);
            if (!"OK".equals(msg)) {
                throw new IllegalArgumentException(msg);
            }

            ApiLog.logApi(logService, IF_ID,ApiLogStep.STEP1, IF_TR_ID, null);
            HashMap<String, IFHMBINNOCEANCDPP0002Dto> resultMap = service.insertObject(dto);

            IFHMBINNOCEANCDPP0002Dto resultDto = resultMap.get("resultDto");

            ApiLog.logApi(logService, IF_ID,ApiLogStep.STEP2, IF_TR_ID, null);

            response = defaultMapper.map(resultDto, IFHMBINNOCEANCDPP0002Payload.Response.class);
            ApiLog.logApi(logService, IF_ID,ApiLogStep.FINISH, IF_TR_ID, JsonUtils.toJson(response));

            IFHMBINNOCEANCDPP0002Dto oldAccount = resultMap.get("oldAccount");

            if(oldAccount != null){
                oldAccount.setApiKey(request.getApiKey());
                service.insertDPObject(oldAccount);
            }else{
                resultDto.setApiKey(request.getApiKey());
                service.insertDPObject(resultDto);
            }
            

        } catch (Exception e) {
            response.setError_spcCode("500");
            response.setError_spcMessage(e.getLocalizedMessage());
            ApiLog.logApi(logService, IF_ID,ApiLogStep.FINISH, IF_TR_ID, JsonUtils.toJson(response), e);
        }

        return response;
    }

    private String isValidRequest(IFHMBINNOCEANCDPP0002Dto dto) {
        String errMsg = "OK";

        //4가지 항목 중 firstname, lastname 은 필수, email 또는 mobile 둘 중 하나라도 없으면 false
        if( (dto.getCpf() == null || dto.getCpf() == "" ) && (dto.getEmailAddress() == null || dto.getEmailAddress() == "") && (dto.getCellPhone() == null || dto.getCellPhone() == "")){
            errMsg = "Either CPF or EmailAddress or PersonMobilePhone must be entered.";
        }else{
            errMsg = "OK";
        }
        return errMsg;
    }
}
