package com.hyundaimotors.hmb.cdppapp.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0005Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBZICARDCDPP0005Payload;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBZICARDCDPP0005Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HMB Account Webservice WF", description = "SAP, EAI Create/Update Interface")
@RestController
@RequiredArgsConstructor
public class IFHMBZICARDCDPP0005Controller {

    @Autowired
    private final IFHMBZICARDCDPP0005Service service;

    private final ModelMapper defaultMapper;
    
    @Operation(summary = "Account insert", description = "Account insert 메서드입니다.")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBZICARDCDPP0005Payload.Response.class)))
    @PostMapping(value = "/api/v1/HMBAccountWebserviceWF/insert")
    public Object insertObject(@RequestBody IFHMBZICARDCDPP0005Payload.Request request)throws Exception{

        IFHMBZICARDCDPP0005Dto dto = defaultMapper.map(request, IFHMBZICARDCDPP0005Dto.class);

        IFHMBZICARDCDPP0005Dto resultDto = service.insertObject(dto);

        return ObjectUtils.isNotEmpty(resultDto) ? defaultMapper.map(resultDto, IFHMBZICARDCDPP0005Payload.Response.class) : null;
    }

    @Operation(summary = "Account update", description = "Account update 메서드입니다.")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBZICARDCDPP0005Payload.Response.class)))
    @PostMapping(value = "/api/v1/HMBAccountWebserviceWF/update")
    public Object updateObject(@RequestBody IFHMBZICARDCDPP0005Payload.Request request)throws Exception{

        IFHMBZICARDCDPP0005Dto dto = defaultMapper.map(request, IFHMBZICARDCDPP0005Dto.class);

        IFHMBZICARDCDPP0005Dto resultDto = service.updateObject(dto);

        return ObjectUtils.isNotEmpty(resultDto) ? defaultMapper.map(resultDto, IFHMBZICARDCDPP0005Payload.Response.class) : null;
    }
}
