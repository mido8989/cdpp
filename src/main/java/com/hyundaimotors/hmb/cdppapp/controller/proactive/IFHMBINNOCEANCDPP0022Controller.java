package com.hyundaimotors.hmb.cdppapp.controller.proactive;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0022Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0032Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0022Payload;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPCDPP0005Payload;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0003Payload;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0022Service;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPCDPP0005Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HMB MNTSRVC Manage Dealer Holiday", description = "Innocean SAP EAI insert Delete Interface")
@RestController
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0022Controller {
    

    @Autowired
    private final IFHMBINNOCEANCDPP0022Service service;

    private final ModelMapper defaultMapper;
    
    @Operation(summary = "dealer holiday insert", description = "dealer holiday insert")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBINNOCEANCDPP0022Payload.Response.class)))
    @PostMapping(value = "/api/v1/HMBMNTSRVCManageDealerHoliday/insert")
    public Object insertObject(@RequestBody IFHMBINNOCEANCDPP0022Payload.Request request)throws Exception{
        IFHMBINNOCEANCDPP0022Dto dto = defaultMapper.map(request, IFHMBINNOCEANCDPP0022Dto.class);
        int resultNum = service.insertObject(dto);

        if(0 < resultNum){
            dto.setErrorSpcCode("0");
            dto.setErrorSpcMessage("OK");
        }else{
            dto.setErrorSpcCode("1");
            dto.setErrorSpcMessage("fail");
        }

        
        return defaultMapper.map(dto, IFHMBINNOCEANCDPP0022Payload.Response.class);
    }

    @Operation(summary = "Account insert", description = "Account insert")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBINNOCEANCDPP0022Payload.Response.class)))
    @PostMapping(value = "/api/v1/HMBMNTSRVCManageDealerHoliday/delete")
    public Object deleteObject(@RequestBody IFHMBINNOCEANCDPP0022Payload.Request request)throws Exception{
        IFHMBINNOCEANCDPP0022Dto dto = defaultMapper.map(request, IFHMBINNOCEANCDPP0022Dto.class);
        int resultNum = service.deleteObject(dto);

        if(0 < resultNum){
            dto.setErrorSpcCode("0");
            dto.setErrorSpcMessage("OK");
        }else{
            dto.setErrorSpcCode("1");
            dto.setErrorSpcMessage("fail");
        }

        
        return defaultMapper.map(dto, IFHMBINNOCEANCDPP0022Payload.Response.class);
    }
}
