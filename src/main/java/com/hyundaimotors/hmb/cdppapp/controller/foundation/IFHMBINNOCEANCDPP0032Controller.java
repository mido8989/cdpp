package com.hyundaimotors.hmb.cdppapp.controller.foundation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0032.IFHMBINNOCEANCDPP0032Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0001Payload;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBZICARDCDPP0032.IFHMBINNOCEANCDPP0032Payload;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0032Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "HMB Search TD Webservice WF", description = "Innocean, SAP EAI list Interface")
@RestController
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0032Controller {
    
    @Autowired
    private final IFHMBINNOCEANCDPP0032Service service;

    private final ModelMapper defaultMapper;

    @Operation(summary = "HMB Search TD Webservice WF", description = "HMB Search TD Webservice WF")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBINNOCEANCDPP0032Payload.Response.class)))
    @GetMapping(value = "/api/v1/HMBSearchTDWebserviceWF")
    @ResponseBody
    public Object getObject(@Valid IFHMBINNOCEANCDPP0032Payload.Request request)throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        ObjectMapper mapper = new ObjectMapper();

        IFHMBINNOCEANCDPP0032Dto resultDto = new IFHMBINNOCEANCDPP0032Dto();

        IFHMBINNOCEANCDPP0032Dto dto = defaultMapper.map(request, IFHMBINNOCEANCDPP0032Dto.class);
        
        resultDto = service.getObject(dto);

        return modelMapper.map(resultDto, IFHMBINNOCEANCDPP0032Payload.Response.class);
    }
}
