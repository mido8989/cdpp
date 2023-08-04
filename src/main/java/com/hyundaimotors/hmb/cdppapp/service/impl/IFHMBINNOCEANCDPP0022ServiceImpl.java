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

    public int insertObject(IFHMBINNOCEANCDPP0022Dto dto)throws Exception{

        List<IFHMBINNOCEANCDPP0022Dto> rowIdList = mapper.getRowId(dto);
        
        int res = 0;
        if(0 == rowIdList.size()){
            
            dto.setRowId(rowIdList.get(0).getRowId());
            dto.setName(rowIdList.get(0).getName());
            
            res = mapper.insertObject(dto);
            
            HashMap<String, String> map = new HashMap<>();
            map.put("PARAM_ID", dto.getHolyRowId());
            map.put("checkcu", "insert");
            
            mapper.transferProcess(map);
        }else{
            dto.setRowId(rowIdList.get(0).getRowId());
            dto.setName(rowIdList.get(0).getName());
            res = mapper.deleteObject(dto);

            HashMap<String, String> map = new HashMap<>();
                map.put("PARAM_ID", dto.getHolyRowId());
                map.put("checkcu", "update");
            
            mapper.transferProcess(map);
        }
        

        return res;
    }

    public int deleteObject(IFHMBINNOCEANCDPP0022Dto dto)throws Exception{
        List<IFHMBINNOCEANCDPP0022Dto> rowIdList = mapper.getRowId(dto);

        dto.setRowId(rowIdList.get(0).getRowId());
        dto.setName(rowIdList.get(0).getName());
        int res = mapper.deleteObject(dto);

        HashMap<String, String> map = new HashMap<>();
            map.put("PARAM_ID", dto.getHolyRowId());
            map.put("checkcu", "update");
            
        mapper.transferProcess(map);
        return res;
    }
}
