package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0007Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBZICARDCDPP0007Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBZICARDCDPP0007Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBZICARDCDPP0007ServiceImpl implements IFHMBZICARDCDPP0007Service{

    private final IFHMBZICARDCDPP0007Mapper mapper;

    public int upsertObject(IFHMBZICARDCDPP0007Dto dto)throws Exception{
        int assetExistNum = mapper.getAssetExistNum(dto);
        int contactExistNum = mapper.getContactExistNum(dto);
        int assetConNum = mapper.getAssetConNum(dto);
        int resultNum = 0;

        resultNum = assetExistNum + contactExistNum;
        if(assetExistNum + contactExistNum == 2 ){
           if(0 < assetConNum){
                mapper.updateObject(dto);
                
                HashMap<String, String> map = new HashMap<String, String>();

                map.put("PARAM_ID", dto.getRowId());

                map.put("checkcu",  "update");
                
                mapper.transferProcess(map);

           }else{
                mapper.insertObject(dto);

                HashMap<String, String> map = new HashMap<String, String>();

                map.put("PARAM_ID", dto.getRowId());

                map.put("checkcu",  "insert");
                
                mapper.transferProcess(map);
           }
        }
        return resultNum;
    }
}
