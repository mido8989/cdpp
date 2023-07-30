package com.hyundaimotors.hmb.cdppapp.controller.foundation;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0002Payload;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0002Service;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBZICARDCDPP0002Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;



public class IFHMBZICARDCDPP0002Controller {
    
    /*@Autowired
    private final IFHMBINNOCEANCDPP0002Service service;

    private final ModelMapper defaultMapper;
    
    @Operation(summary = "Contact insert", description = "Contact insert")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBZICARDCDPP0002Payload.Response.class)))
    @PostMapping(value = "/insert")
    public Object insertObject(@RequestBody IFHMBZICARDCDPP0002Payload.Request request)throws Exception{

        if(request.getSuppressallemails()==null){
            request.setSuppressallemails("Y");
        }

        IFHMBINNOCEANCDPP0002Dto dto = defaultMapper.map(request, IFHMBINNOCEANCDPP0002Dto.class);

        IFHMBINNOCEANCDPP0002Dto resultDto = service.insertObject(dto);

        return ObjectUtils.isNotEmpty(resultDto) ? defaultMapper.map(resultDto, IFHMBZICARDCDPP0002Payload.Response.class) : null;
        
    }*/
}
