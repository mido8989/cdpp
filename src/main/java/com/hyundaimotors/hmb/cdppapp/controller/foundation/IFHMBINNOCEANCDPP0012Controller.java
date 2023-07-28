package com.hyundaimotors.hmb.cdppapp.controller.foundation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBDMSCDPP0004Payload;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0012Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0012Payload;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0012Payload.ChannelPartner;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0012Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HMB Channel Partner Get Webservice Inbound WF", description = "Innocean Account, Contact list Interface")
@RestController
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0012Controller {
    @Autowired
    private final IFHMBINNOCEANCDPP0012Service service;

    private final ModelMapper defaultMapper;

    @Operation(summary = "Account Contact list", description = "Account Contact imformation list")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBINNOCEANCDPP0012Payload.Response.class)))
    @PostMapping(value = "/api/v1/HMBChannelPartnerGetWebserviceInboundWF")
    public Object getObject(@RequestBody IFHMBINNOCEANCDPP0012Payload.Request request)throws Exception{

        ModelMapper modelMapper = new ModelMapper();

        Map<String, Object> response = new HashMap<>();
        
        
        List<IFHMBINNOCEANCDPP0012Payload.ChannelPartnerContact> channelPartnerContact = new ArrayList<>();

        IFHMBINNOCEANCDPP0012Dto dto = defaultMapper.map(request, IFHMBINNOCEANCDPP0012Dto.class);

        IFHMBINNOCEANCDPP0012Dto channelPartnerDto = service.getChannelPartner(dto);

        List<IFHMBINNOCEANCDPP0012Dto> list = service.getChannelPartnerContactList(dto);
        
        if(channelPartnerDto == null){
           response.put("channelPartner", null);
        }else{
            IFHMBINNOCEANCDPP0012Payload.ChannelPartner channelPartner = new IFHMBINNOCEANCDPP0012Payload.ChannelPartner();
            channelPartner = modelMapper.map(channelPartnerDto, IFHMBINNOCEANCDPP0012Payload.ChannelPartner.class);
            response.put("channelPartner", channelPartner);
        }
        

        if(list.size() != 0){
            for(int i = 0;i < list.size(); i++){
            
                IFHMBINNOCEANCDPP0012Payload.ChannelPartnerContact channelPartnerContactDto = new IFHMBINNOCEANCDPP0012Payload.ChannelPartnerContact();
                
                channelPartnerContactDto = modelMapper.map(list.get(i), IFHMBINNOCEANCDPP0012Payload.ChannelPartnerContact.class);
                
                channelPartnerContact.add(i, channelPartnerContactDto);
            
            }
            
        }else{
            channelPartnerContact.add(0, null);
        }
        
        response.put("channelPartnerContact", channelPartnerContact);

        return response;
    }
}
