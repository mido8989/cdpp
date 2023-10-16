package com.hyundaimotors.hmb.cdppapp.controller.foundation;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002.IFHMBINNOCEANCDPP0002Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0002Payload;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0002Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HMB Inbound Contact Interface Workflow", description = "INNOCEAN, SAP EAI insert Interface")
@RestController
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0002Controller {

    private final IFHMBINNOCEANCDPP0002Service service;

    private final ModelMapper defaultMapper;
    
    @Operation(summary = "Person Account Insert", description = "Person Account Insert.")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBINNOCEANCDPP0002Payload.Request.class)))
    @PostMapping(value = "/api/v1/HMBInboundContactInterfaceWorkflow")
    public Object insertInboundContactWorkflow(@RequestBody IFHMBINNOCEANCDPP0002Payload.Request request) throws Exception {

        IFHMBINNOCEANCDPP0002Dto dto = defaultMapper.map(request, IFHMBINNOCEANCDPP0002Dto.class);

        IFHMBINNOCEANCDPP0002Dto resultDto = service.insertObject(dto);


        return defaultMapper.map(resultDto, IFHMBINNOCEANCDPP0002Payload.Response.class);
    }
}


