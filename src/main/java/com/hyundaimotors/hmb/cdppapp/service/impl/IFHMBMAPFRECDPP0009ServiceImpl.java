package com.hyundaimotors.hmb.cdppapp.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBMAPFRECDPP0009Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0046Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBMAPFRECDPP0009Mapper;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBMAPFRECDPP0009.IFHMBMAPFRECDPP0009RSAServicePayload;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBMAPFRECDPP0009Service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBMAPFRECDPP0009ServiceImpl implements IFHMBMAPFRECDPP0009Service{
    
    private final IFHMBMAPFRECDPP0009Mapper mapper;

    public IFHMBMAPFRECDPP0009Dto upsertObject(IFHMBMAPFRECDPP0009Dto dto)throws Exception{
        IFHMBMAPFRECDPP0009Dto resultDto = new IFHMBMAPFRECDPP0009Dto();

        List<IFHMBMAPFRECDPP0009RSAServicePayload> resSetviceList = new ArrayList<>();
        resSetviceList = dto.getRsaSetvice();
        
        int assetCount = 0; // Asset 정보 확인
        int rowIdCount = 0; // RSA 테이블 Upsert 여부
        int resSetviceNum = 0;
        int resultNum = 0;
        
        assetCount = mapper.getAssetCount(dto);

        rowIdCount = mapper.getCheckoutRowId(dto);

       

        if(0 < assetCount){
            if(0 < rowIdCount){
                resultNum = mapper.updateObject(dto);

                if(resSetviceList.size() > 0){
                    for(int i=0; i<resSetviceList.size();i++){
                        int rasServiceCheckNum = 0;
                        IFHMBMAPFRECDPP0009RSAServicePayload rasService = new IFHMBMAPFRECDPP0009RSAServicePayload();

                        rasService = resSetviceList.get(i);

                        rasServiceCheckNum = mapper.rasServiceCheck(rasService);
                        
                        if(0 < rasServiceCheckNum){
                            resSetviceNum = mapper.updateResSetvice(rasService);
                        }else{
                            resSetviceNum = mapper.insertResSetvice(rasService);
                        }
                    }
                }

            }else{
                resultNum = mapper.insertObject(dto);
            }
            
            if(0 < resultNum){
                resultDto.setErrorSpcCode("0");
                resultDto.setErrorSpcMessage("OK");
            }else{
                resultDto.setErrorSpcCode("1");
                resultDto.setErrorSpcMessage("fail");
            }
            
        }
        
        return resultDto;
    }
}
