package com.hyundaimotors.hmb.cdppapp.controller.foundation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0046Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0026Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0003Payload;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0026.IFHMBSAPEAICDPP0026Payload;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0026.ListOfProduct;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPEAICDPP0003Service;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPEAICDPP0026Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HMB Product List Inbound WF", description = "SAP EAI Product Create/Update Interface")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/HMBProductListInboundWF")
public class IFHMBSAPEAICDPP0026Controller {
    
    @Autowired
    private final IFHMBSAPEAICDPP0026Service service;

    private final ModelMapper defaultMapper;

    @Operation(summary = "Poduct insert", description = "Poduct insert")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBSAPEAICDPP0026Payload.Response.class)))
    @PostMapping(value = "/insert")
    public Object insertList(@RequestBody List<ListOfProduct> request)throws Exception{
        List<IFHMBSAPEAICDPP0026Dto> list = new ArrayList<>();
        for(int i=0; i < request.size(); i++){
            list.add(i, defaultMapper.map(request.get(i), IFHMBSAPEAICDPP0026Dto.class));
        }
        int listSize = list.size();

        int resultNum = service.insertList(list);

        List<IFHMBSAPEAICDPP0026Payload.Response> UpsertProductList = new ArrayList<>();
        
        if(listSize == resultNum){
            for(int j=0; j< listSize; j++){
                IFHMBSAPEAICDPP0026Payload.Response response = new IFHMBSAPEAICDPP0026Payload.Response();
                response.setErrorSpcCode("0");
                response.setErrorSpcMessage("OK");
                UpsertProductList.add(j, response);
            }
        }else{
            IFHMBSAPEAICDPP0026Payload.Response response = new IFHMBSAPEAICDPP0026Payload.Response();
            response.setErrorSpcCode("1");
            response.setErrorSpcMessage("fail");
            UpsertProductList.add(0, response);
        }

        return UpsertProductList;
    }

    @Operation(summary = "Poduct update", description = "Poduct update")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBSAPEAICDPP0026Payload.Response.class)))
    @PostMapping(value = "/update")
    public Object updateList(@RequestBody List<ListOfProduct> request)throws Exception{
        List<IFHMBSAPEAICDPP0026Dto> list = new ArrayList<>();
        for(int i=0; i < request.size(); i++){
            list.add(i, defaultMapper.map(request.get(i), IFHMBSAPEAICDPP0026Dto.class));
        }
        int listSize = list.size();

        int resultNum = service.updateList(list);

        List<IFHMBSAPEAICDPP0026Payload.Response> UpsertProductList = new ArrayList<>();
        
        if(listSize == resultNum){
            for(int j=0; j< listSize; j++){
                IFHMBSAPEAICDPP0026Payload.Response response = new IFHMBSAPEAICDPP0026Payload.Response();
                response.setErrorSpcCode("0");
                response.setErrorSpcMessage("OK");
                UpsertProductList.add(j, response);
            }
        }else{
            IFHMBSAPEAICDPP0026Payload.Response response = new IFHMBSAPEAICDPP0026Payload.Response();
            response.setErrorSpcCode("1");
            response.setErrorSpcMessage("fail");
            UpsertProductList.add(0, response);
        }

        return UpsertProductList;
    }
}
