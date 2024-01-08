package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;
import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0010.AutoVehicleWebserviceWFAccountDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0010.AutoVehicleWebserviceWFContactDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0010.IFHMBSAPEAICDPP0010Dto;

public interface IFHMBSAPEAICDPP0010Mapper {
    
    public String getProcAssetId(IFHMBSAPEAICDPP0010Dto dto) throws Exception;
    
    public String getProcInvoiceIdCon(IFHMBSAPEAICDPP0010Dto dto) throws Exception;

    public String getProcInvoiceIdAcc(IFHMBSAPEAICDPP0010Dto dto) throws Exception;

    public List<String> getProcCustVehicleIdCon(IFHMBSAPEAICDPP0010Dto dto) throws Exception;

    public List<String> getProcCustVehicleIdAcc(IFHMBSAPEAICDPP0010Dto dto) throws Exception;
        
    public void insertAutoVehicle(IFHMBSAPEAICDPP0010Dto dto) throws Exception;

    public void insertContactList(AutoVehicleWebserviceWFContactDto dto) throws Exception;
    
    public void insertAccountList(AutoVehicleWebserviceWFAccountDto dto) throws Exception;

    public void transferProcess(HashMap<String, String[]> parMap)throws Exception;

    public void transferDPProcess(HashMap<String, String> parMap)throws Exception;

    public void transferReplica(HashMap<String, String[]> parMap)throws Exception;
    
}