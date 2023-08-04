package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0022Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0022Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0022Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0022ServiceImpl implements IFHMBINNOCEANCDPP0022Service{
    
    private final IFHMBINNOCEANCDPP0022Mapper mapper;   

    public int manageObject(IFHMBINNOCEANCDPP0022Dto dto)throws Exception{

        List<IFHMBINNOCEANCDPP0022Dto> rowIdList = mapper.getCheckHolyDay(dto);
        int res = 0;
        if("upsert".equalsIgnoreCase(dto.getOperation())){
            if(rowIdList.size() > 0) {
            
            //IFHMBINNOCEANCDPP0022Dto rowIdDto = mapper.getRowId(dto); // x_org_ext.row_id
            IFHMBINNOCEANCDPP0022Dto rowIdDto = rowIdList.get(0);
            dto.setHolyRowId(rowIdDto.getHolyRowId());
            
            res = mapper.updateObject(dto);

            HashMap<String, String> map = new HashMap<>();
                map.put("PARAM_ID", rowIdDto.getHolyRowId());
                map.put("checkcu", "update");
            
            mapper.transferProcess(map);
            
            }else{
                IFHMBINNOCEANCDPP0022Dto rowIdDto = mapper.getRowId(dto);
                dto.setRowId(rowIdDto.getRowId());
                res = mapper.insertObject(dto);
                
                HashMap<String, String> map = new HashMap<>();
                map.put("PARAM_ID", dto.getHolyRowId());
                map.put("checkcu", "insert");
                
                mapper.transferProcess(map);
                
            }
        }else if("delete".equalsIgnoreCase(dto.getOperation())){
            if(rowIdList.size() > 0) {
                IFHMBINNOCEANCDPP0022Dto rowIdDto = rowIdList.get(0);
                dto.setHolyRowId(rowIdDto.getHolyRowId());
                
                HashMap<String, String> map = new HashMap<>();
                map.put("PARAM_ID", dto.getHolyRowId());
                map.put("checkcu", "delete");

                mapper.transferProcess(map);
                
                res = mapper.deleteObject(dto);

            }
        }

        return res;
    }
}
