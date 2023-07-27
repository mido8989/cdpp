package com.hyundaimotors.hmb.cdppapp.controller.lead;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBDMSCDPP0004Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0032Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBDMSCDPP0004Payload;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0001Payload;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBDMSCDPP0004Payload.Veiculo;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBDMSCDPP0004Service;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0001Service;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HMB Get Lead WS", description = "DMS list Interface")
@RestController
@RequiredArgsConstructor
public class IFHMBDMSCDPP0004Controller {
    
    @Autowired
    private final IFHMBDMSCDPP0004Service service;

    private final ModelMapper defaultMapper;



    @Operation(summary = "Lead list", description = "Lead list.")
    @ApiResponse(content = @Content(schema = @Schema(implementation = IFHMBDMSCDPP0004Payload.Response.class)))
    @PostMapping(value = "/api/v1/HMBGetLeadWS")
    public Object getObject(@RequestBody IFHMBDMSCDPP0004Payload.Request request)throws Exception{
        ModelMapper modelMapper = new ModelMapper();

        IFHMBDMSCDPP0004Dto dto = defaultMapper.map(request, IFHMBDMSCDPP0004Dto.class);
        
        List<IFHMBDMSCDPP0004Dto> rowIdList = service.getContact(dto);

        List<Map<String, Object>> itemList = new ArrayList<>();

        for(int i=0;i< rowIdList.size(); i++){
            Map<String, Object> result = new HashMap<String, Object>();
            List<IFHMBDMSCDPP0004Dto> resultVeiculoList = new ArrayList<>();
            IFHMBDMSCDPP0004Payload.Contact contact = new IFHMBDMSCDPP0004Payload.Contact();
            List<IFHMBDMSCDPP0004Payload.Veiculo> veiculo = new ArrayList<>();
            IFHMBDMSCDPP0004Payload.Opv opv = new IFHMBDMSCDPP0004Payload.Opv();
            IFHMBDMSCDPP0004Payload.FinanciamentoOPV financiamentoOPV = new IFHMBDMSCDPP0004Payload.FinanciamentoOPV();

            IFHMBDMSCDPP0004Dto resultContactDto = service.contactInfoList(rowIdList.get(i));

            contact = ObjectUtils.isNotEmpty(resultContactDto) ? modelMapper.map(resultContactDto, IFHMBDMSCDPP0004Payload.Contact.class) : null;

            result.put("contact", contact);

            resultVeiculoList = service.veiculoInfoList(rowIdList.get(i));

            if(resultVeiculoList.size() != 0){
                for(int j = 0;j < resultVeiculoList.size(); j++){
                
                    IFHMBDMSCDPP0004Payload.Veiculo veiculoVo = new IFHMBDMSCDPP0004Payload.Veiculo();
                    veiculoVo = modelMapper.map(resultVeiculoList.get(j), IFHMBDMSCDPP0004Payload.Veiculo.class);
                    veiculo.add(j, veiculoVo);
                
                }
            }else{
                veiculo.add(0, null);
            }
            
            
            result.put("veiculo", veiculo);

            
            IFHMBDMSCDPP0004Dto resultOpvDto = service.opvInfoList(rowIdList.get(i));
            
            opv = ObjectUtils.isNotEmpty(resultOpvDto) ? modelMapper.map(resultOpvDto, IFHMBDMSCDPP0004Payload.Opv.class) : null;

            result.put("opv", opv);

            if(resultOpvDto != null){
                IFHMBDMSCDPP0004Dto resultFinanciamentoopvDto = service.financiamentoopvList(resultOpvDto);

                financiamentoOPV = ObjectUtils.isNotEmpty(resultFinanciamentoopvDto) ? modelMapper.map(resultFinanciamentoopvDto, IFHMBDMSCDPP0004Payload.FinanciamentoOPV.class) : null;
                result.put("financiamentoOPV", financiamentoOPV);
            }else{
                result.put("financiamentoOPV", null);
            }


            itemList.add(i, result);
        }

        return itemList;
    }
}
