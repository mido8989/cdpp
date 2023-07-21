package com.hyundaimotors.hmb.cdppapp.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0001Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBZICARDCDPP0001Payload;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBZICARDCDPP0001Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HMB Get Contact WF", description = "Zicard, EAI 조회 Interface")
@RestController
@RequiredArgsConstructor
public class IFHMBZICARDCDPP0001Controller {
    
    @Autowired
    private final IFHMBZICARDCDPP0001Service service;

    private final ModelMapper defaultMapper;



    @Operation(summary = "Account 조회", description = "Account 조회 메서드입니다.")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBZICARDCDPP0001Payload.Response.class)))
    @PostMapping(value = "/api/v1/HMBGetContactWF")
    public Object getObject(@RequestBody IFHMBZICARDCDPP0001Payload.Request request)throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        
        IFHMBZICARDCDPP0001Dto resultDto = new IFHMBZICARDCDPP0001Dto();

        IFHMBZICARDCDPP0001Dto dto = modelMapper.map(request, IFHMBZICARDCDPP0001Dto.class);

        resultDto = service.getObject(dto);

        return ObjectUtils.isNotEmpty(resultDto) ? modelMapper.map(resultDto, IFHMBZICARDCDPP0001Payload.Response.class) : null;       
    }
}
