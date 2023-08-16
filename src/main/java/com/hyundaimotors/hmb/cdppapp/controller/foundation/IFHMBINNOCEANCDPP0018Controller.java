package com.hyundaimotors.hmb.cdppapp.controller.foundation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0001Payload;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0018Payload;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0018Service;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0021Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HMB MNTSRVC Customer Schedule", description = "This web service can be used to query the customer's maintenance service schedule.")
@RestController
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0018Controller {
    @Autowired
    private final IFHMBINNOCEANCDPP0018Service service;

    private final ModelMapper defaultMapper;

    @Operation(summary = "Account list", description = "Account list.")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBINNOCEANCDPP0018Payload.Response.class)))
    @PostMapping(value = "/api/v1/HMBGetContactWF")
    public Object getObject(@RequestBody IFHMBINNOCEANCDPP0018Payload.Request request)throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        
        return "";
    }    
}
