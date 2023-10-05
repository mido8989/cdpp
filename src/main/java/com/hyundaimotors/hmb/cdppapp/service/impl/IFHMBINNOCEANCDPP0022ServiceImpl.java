package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0022.IFHMBINNOCEANCDPP0022Dto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0022.ManageDealerHolidayOutputDto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0022Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0022Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0022ServiceImpl implements IFHMBINNOCEANCDPP0022Service{
    
    private final IFHMBINNOCEANCDPP0022Mapper mapper;   

    public IFHMBINNOCEANCDPP0022Dto insertObject(IFHMBINNOCEANCDPP0022Dto dto)throws Exception{

        IFHMBINNOCEANCDPP0022Dto resultDto = new IFHMBINNOCEANCDPP0022Dto();
        List<IFHMBINNOCEANCDPP0022Dto> rowIdList = mapper.getCheckHoliDay(dto);

        mapper.insertObject(dto);

        String procDealerHolidayRowId = mapper.getRowId(dto);

        if("upsert".equalsIgnoreCase(dto.getOperation())){

            if(rowIdList.size() > 0) {
            
            IFHMBINNOCEANCDPP0022Dto rowIdDto = rowIdList.get(0);
            dto.setHolyRowId(rowIdDto.getHolyRowId());

            

            HashMap<String, String> map = new HashMap<>();
            map.put("PARAM_ID", String.valueOf(dto.getHolyRowId()));
            map.put("PROC_ID", procDealerHolidayRowId);
            map.put("checkcu", "update");
            
            mapper.transferProcess(map);
            mapper.transferReplica(map);
            
            ManageDealerHolidayOutputDto manageDealerHolidayOutput = new ManageDealerHolidayOutputDto();
            
            manageDealerHolidayOutput.setErrorSpcCode("0");
            manageDealerHolidayOutput.setErrorSpcMessage("OK");
            
            resultDto.setManageDealerHolidayOutput(manageDealerHolidayOutput);
                
            }else{            
                
                HashMap<String, String> map = new HashMap<>();
                map.put("PARAM_ID", String.valueOf(dto.getHolyRowId()));
                map.put("checkcu", "insert");
                
                mapper.transferProcess(map);
                
                procDealerHolidayRowId = mapper.getRowId(dto);
                
                map.put("PROC_ID", procDealerHolidayRowId);


                mapper.transferReplica(map);

                ManageDealerHolidayOutputDto manageDealerHolidayOutput = new ManageDealerHolidayOutputDto();
            
                manageDealerHolidayOutput.setErrorSpcCode("0");
                manageDealerHolidayOutput.setErrorSpcMessage("OK");
                
                resultDto.setManageDealerHolidayOutput(manageDealerHolidayOutput);
            }            

        }else if("delete".equalsIgnoreCase(dto.getOperation())){

            if(rowIdList.size() > 0) {
                IFHMBINNOCEANCDPP0022Dto rowIdDto = rowIdList.get(0);
                dto.setHolyRowId(rowIdDto.getHolyRowId());
                
                HashMap<String, String> map = new HashMap<>();
                map.put("PARAM_ID", String.valueOf(dto.getHolyRowId()));
                map.put("PROC_ID", procDealerHolidayRowId);
                map.put("checkcu", "delete");

                mapper.transferProcess(map);
                mapper.transferReplica(map);
                
                mapper.insertObject(dto);

                ManageDealerHolidayOutputDto manageDealerHolidayOutput = new ManageDealerHolidayOutputDto();
            
                manageDealerHolidayOutput.setErrorSpcCode("0");
                manageDealerHolidayOutput.setErrorSpcMessage("OK");
                
                resultDto.setManageDealerHolidayOutput(manageDealerHolidayOutput);
            }
        }

        

        return resultDto;       
    }
}
