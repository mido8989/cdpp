package com.hyundaimotors.hmb.cdppapp.controller;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0032Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBZICARDCDPP0032Payload;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBZICARDCDPP0032Payload.GetContactTD;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBZICARDCDPP0032Payload.SearchContactTD;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBZICARDCDPP0032Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HMB Search TD Webservice WF", description = "ZICARD, SAP EAI list Interface")
@RestController
@RequiredArgsConstructor
public class IFHMBZICARDCDPP0032Controller {
    
    @Autowired
    private final IFHMBZICARDCDPP0032Service service;

    private final ModelMapper defaultMapper;



    @Operation(summary = "Account list", description = "Account imformation list")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBZICARDCDPP0032Payload.Response.class)))
    @PostMapping(value = "/api/v1/HMBSearchTDWebserviceWF")
    public Object getObject(@RequestBody IFHMBZICARDCDPP0032Payload.Request request)throws Exception{
        SearchContactTD searchContactTD = new SearchContactTD();
        GetContactTD getContactTD = new GetContactTD();

        IFHMBZICARDCDPP0032Dto dto = defaultMapper.map(request, IFHMBZICARDCDPP0032Dto.class);
        
        IFHMBZICARDCDPP0032Dto searchContactDto = service.getSearchContact(dto);

        List<IFHMBZICARDCDPP0032Dto> list = service.getlistOfServiceRequest(dto);

        getContactTD.setErrorSpcCode("200");
        getContactTD.setErrorSpcMessage("success");
        
        
        return IFHMBZICARDCDPP0032Payload.Response.builder()
                .getContactTD(getContactTD)
                .searchContactTD(ObjectUtils.isNotEmpty(searchContactDto) ? defaultMapper.map(searchContactDto, SearchContactTD.class) : null)
                //.searchContactTD(defaultMapper.map(searchContactDto, SearchContactTD.class))
                .listOfServiceRequest(list.size() > 0 ? defaultMapper.map(list, new TypeToken<List<IFHMBZICARDCDPP0032Payload.ListOfServiceRequest>>() {}.getType()) : null)
                .build();
    }
}
