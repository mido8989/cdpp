package com.hyundaimotors.hmb.cdppapp.controller.foundation;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0046Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0028Payload;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0046.IFHMBINNOCEANCDPP0046Payload;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0046Service;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HMB MNTSRVC Manage Dealer Service", description = "Innocean, SAP EAI list Interface")
@RestController
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0046Controller {
    
    @Autowired
    private final IFHMBINNOCEANCDPP0046Service service;

    private final ModelMapper defaultMapper;

    @Operation(summary = "dealer Manage list", description = "dealer Manage list.")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBINNOCEANCDPP0046Payload.Response.class)))
    @PostMapping(value = "/api/v1/HMBMNTSRVCManageDealerService")
    public Object getList(@RequestBody IFHMBINNOCEANCDPP0046Payload.Request request)throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        IFHMBINNOCEANCDPP0046Dto dto = defaultMapper.map(request, IFHMBINNOCEANCDPP0046Dto.class);

        IFHMBINNOCEANCDPP0046Dto resultDto = service.insertList(dto);
        if(resultDto != null){
            return defaultMapper.map(dto, IFHMBINNOCEANCDPP0046Payload.Response.class);
            
        }else{
            IFHMBINNOCEANCDPP0046Payload.Response failResponseDto = new IFHMBINNOCEANCDPP0046Payload.Response();
            failResponseDto.setErrorSpcCode("1");
            failResponseDto.setErrorSpcMessage("fail");
            return failResponseDto;
        }
        
    }
}
