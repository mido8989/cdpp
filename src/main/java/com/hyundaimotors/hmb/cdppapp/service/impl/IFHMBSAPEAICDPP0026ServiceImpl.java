package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0026Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBSAPEAICDPP0003Mapper;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBSAPEAICDPP0026Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPEAICDPP0026Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBSAPEAICDPP0026ServiceImpl implements IFHMBSAPEAICDPP0026Service{
    
    private final IFHMBSAPEAICDPP0026Mapper mapper;

    public int insertList(List<IFHMBSAPEAICDPP0026Dto> list)throws Exception{
            int res = mapper.insertList(list);

            for(int i=0; i < list.size(); i++){
                list.get(i).getRowId();
                HashMap<String, String> map = new HashMap<>();
                map.put("PARAM_ID", list.get(i).getRowId());
                map.put("checkcu", "insert");
                mapper.transferProcess(map);

                HashMap<String, String> replicaMap = new HashMap<>();
                String externalId = mapper.processPoductId(list.get(i).getRowId());
                replicaMap.put("PARAM_ID", externalId);
                replicaMap.put("checkcu", "insert");

                mapper.transferReplica(replicaMap);
            }
        return res;
    }

    public int updateList(List<IFHMBSAPEAICDPP0026Dto> list)throws Exception{

        return mapper.updateList(list);
    }
}
