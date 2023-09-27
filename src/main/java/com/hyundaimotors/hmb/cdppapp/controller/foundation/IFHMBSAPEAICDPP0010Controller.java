package com.hyundaimotors.hmb.cdppapp.controller.foundation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0010Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0010Payload;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPEAICDPP0010Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HMB Auto Vehicle Webservice WF", description = "UpsertAutoVehicle")
@RestController
@RequiredArgsConstructor
public class IFHMBSAPEAICDPP0010Controller {

    @Autowired
    private final IFHMBSAPEAICDPP0010Service service;
    // private final ModelMapper defaultMapper;

    @Operation(summary = "asset upsert", description = "asset upsert")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBSAPEAICDPP0010Payload.Response.class)))
    @PostMapping(value = "/api/v1/HMBAutoVehicleUpsertWS")
    public Object upserObject(@RequestBody IFHMBSAPEAICDPP0010Payload.Request request) throws Exception{
        
        ModelMapper modelMapper = new ModelMapper();

        IFHMBSAPEAICDPP0010Dto dto = modelMapper.map(request, IFHMBSAPEAICDPP0010Dto.class);

        IFHMBSAPEAICDPP0010Dto resultDto = service.UpsertAutoVehicle(dto);

        return modelMapper.map(resultDto, IFHMBSAPEAICDPP0010Payload.Response.class);

    }
}
