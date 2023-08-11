package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;
import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0010Dto;
import com.hyundaimotors.hmb.cdppapp.payload.AutoVehicleWebserviceWFAccountPayload;
import com.hyundaimotors.hmb.cdppapp.payload.AutoVehicleWebserviceWFContactPayload;

public interface IFHMBSAPEAICDPP0010Mapper {
    
    public List<IFHMBSAPEAICDPP0010Dto> getRowCheck(IFHMBSAPEAICDPP0010Dto dto) throws Exception;
        
    public int InsertAutoVehicle(IFHMBSAPEAICDPP0010Dto dto) throws Exception;

    public int UpdateSConDtoList(List<AutoVehicleWebserviceWFContactPayload> contact) throws Exception;

    public int InsertSAssetXDto(IFHMBSAPEAICDPP0010Dto dto) throws Exception;
    
    public int InsertSAssetAtxDto(IFHMBSAPEAICDPP0010Dto dto) throws Exception;
    
    public int InsertSAssetConDtoList(List<AutoVehicleWebserviceWFContactPayload> contact) throws Exception;
    
    public int InsertSAssetAccDtoList(List<AutoVehicleWebserviceWFAccountPayload> account) throws Exception;
    
    public int UpdateAutoVehicle(IFHMBSAPEAICDPP0010Dto dto) throws Exception;
    
    public int UpdateSAssetXDto(IFHMBSAPEAICDPP0010Dto dto) throws Exception;
    
    public int UpdateSAssetAtxDto(IFHMBSAPEAICDPP0010Dto dto) throws Exception;

    public int UpdateSAssetConDtoList(List<AutoVehicleWebserviceWFContactPayload> contact) throws Exception;
    
    public int UpdateSAssetAccntDtoList(List<AutoVehicleWebserviceWFAccountPayload> account) throws Exception;

    public void transferProcess(HashMap<String, String> parMap)throws Exception;

    public void transferReplica(HashMap<String, String> replicaMap)throws Exception;
    
}