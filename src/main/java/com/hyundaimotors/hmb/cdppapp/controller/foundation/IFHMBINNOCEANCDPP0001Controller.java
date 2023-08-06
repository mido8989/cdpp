package com.hyundaimotors.hmb.cdppapp.controller.foundation;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0001Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0001Payload;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0001Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HMB Get Contact WF", description = "INNOCEAN, EAI list Interface")
@RestController
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0001Controller {
    
    @Autowired
    private final IFHMBINNOCEANCDPP0001Service service;

    private final ModelMapper defaultMapper;



    @Operation(summary = "Account list", description = "Account list.")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBINNOCEANCDPP0001Payload.Response.class)))
    @PostMapping(value = "/api/v1/HMBGetContactWF")
    public Object getObject(@RequestBody IFHMBINNOCEANCDPP0001Payload.Request request)throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        
        IFHMBINNOCEANCDPP0001Dto resultDto = new IFHMBINNOCEANCDPP0001Dto();

        IFHMBINNOCEANCDPP0001Dto dto = modelMapper.map(request, IFHMBINNOCEANCDPP0001Dto.class);

        resultDto = service.getObject(dto);

        if(resultDto == null){
            resultDto.setErrorSpcCode("1");
            resultDto.setErrorSpcCode("no data");

            return modelMapper.map(resultDto, IFHMBINNOCEANCDPP0001Payload.Response.class);
        }else{
            return ObjectUtils.isNotEmpty(resultDto) ? modelMapper.map(resultDto, IFHMBINNOCEANCDPP0001Payload.Response.class) : null;
        }

               
    }
}
