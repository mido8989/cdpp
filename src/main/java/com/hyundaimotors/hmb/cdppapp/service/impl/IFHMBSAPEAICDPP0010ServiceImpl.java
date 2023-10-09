package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0010.AutoVehicleWebserviceWFAccountDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0010.AutoVehicleWebserviceWFContactDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0010.IFHMBSAPEAICDPP0010Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBSAPEAICDPP0010Mapper;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBSAPEAICDPP0010Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBSAPEAICDPP0010ServiceImpl implements IFHMBSAPEAICDPP0010Service {

    private final IFHMBSAPEAICDPP0010Mapper mapper;
    
    public HashMap<String, Object> UpsertAutoVehicle(IFHMBSAPEAICDPP0010Dto dto)throws Exception{

        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, String[]> processMap = new HashMap<>();

        List<AutoVehicleWebserviceWFContactDto> listOfContact = new ArrayList<>();
        List<AutoVehicleWebserviceWFAccountDto> listOfAccount = new ArrayList<>();
        List<String> listParamId = new ArrayList<>();
        List<String> listProcAssetId = new ArrayList<>();
        List<String> listProcInvoiceId = new ArrayList<>();
        List<String> listContactId = new ArrayList<>();
        List<String> listAccontId = new ArrayList<>();
        List<String> listProcCustVehicleId = new ArrayList<>();
        
        String getAssetId = mapper.getProcAssetId(dto);
        String getProcInvoiceId = mapper.getProcInvoiceId(dto);

        mapper.insertAutoVehicle(dto);        

        listParamId.add(dto.getRowId());
        if(getAssetId != null)
            listProcAssetId.add(getAssetId);
        if(getProcInvoiceId != null)
            listProcInvoiceId.add(getProcInvoiceId);
        listOfContact = dto.getContact();
        listOfAccount = dto.getAccount();

        if( listOfContact.size() > 0 ){
            for(int i = 0; i < listOfContact.size(); i++){
                AutoVehicleWebserviceWFContactDto con = new AutoVehicleWebserviceWFContactDto();
                con = listOfContact.get(i);
                con.setSerialNumber(dto.getSerialNumber());
                con.setParRowId(dto.getRowId());
                mapper.insertContactList(con);
                listContactId.add(String.valueOf(con.getConRowId()));
            }
        }

        if( listOfAccount.size() > 0 ){
            for(int i = 0; i < listOfAccount.size(); i++){
                AutoVehicleWebserviceWFAccountDto accnt = new AutoVehicleWebserviceWFAccountDto();
                accnt = listOfAccount.get(i);
                accnt.setSerialNumber(dto.getSerialNumber());
                accnt.setParRowId(dto.getRowId());
                mapper.insertAccountList(accnt);
                listAccontId.add(String.valueOf(accnt.getAccntRowId()));
            }
        }     
        
        String[] paramId = listParamId.toArray(new String[listParamId.size()]);  
        String[] procAssetId = listProcAssetId.toArray(new String[listProcAssetId.size()]);
        String[] procInvoiceId = listProcInvoiceId.toArray(new String[listProcInvoiceId.size()]);
        String[] contactId = listContactId.toArray(new String[listContactId.size()]);
        String[] accountId = listAccontId.toArray(new String[listAccontId.size()]);


        
        processMap.put("PARAM_ID", paramId);
        processMap.put("PROC_ASSET_ID", procAssetId);
        processMap.put("PROC_INVOICE_ID", procInvoiceId);
        processMap.put("account_rowid", accountId);
        processMap.put("contact_rowid", contactId); 
     
        if ( getAssetId != null && getProcInvoiceId != null){


            mapper.transferProcess(processMap);

            listProcCustVehicleId = mapper.getProcCustVehicleId(dto);
            String[] procCustVehicleId = listProcCustVehicleId.toArray(new String[listProcCustVehicleId.size()]);
            processMap.put("PROC_CUSTVEHICLE_ID", procCustVehicleId);
            mapper.transferReplica(processMap);

            map.put("errorSpcCode", "0");
            map.put("errorSpcMessage", "OK"); 

        }else if(getProcInvoiceId != null && getAssetId == null ) {

            mapper.transferProcess(processMap);

            
            getAssetId = mapper.getProcAssetId(dto);
            listProcCustVehicleId = mapper.getProcCustVehicleId(dto);
            listProcAssetId.add(getAssetId);
            procAssetId = listProcAssetId.toArray(new String[listProcAssetId.size()]);
            String[] procCustVehicleId = listProcCustVehicleId.toArray(new String[listProcCustVehicleId.size()]);
            processMap.put("PROC_ASSET_ID", procAssetId);
            processMap.put("PROC_CUSTVEHICLE_ID", procCustVehicleId);

            mapper.transferReplica(processMap);

            map.put("errorSpcCode", "0");
            map.put("errorSpcMessage", "OK"); 

        }else if(getAssetId != null && getProcInvoiceId == null){


            mapper.transferProcess(processMap);

            
            getProcInvoiceId = mapper.getProcInvoiceId(dto);
            listProcCustVehicleId = mapper.getProcCustVehicleId(dto);
            listProcInvoiceId.add(getProcInvoiceId);
            procInvoiceId = listProcInvoiceId.toArray(new String[listProcInvoiceId.size()]);
            String[] procCustVehicleId = listProcCustVehicleId.toArray(new String[listProcCustVehicleId.size()]);
            processMap.put("PROC_INVOICE_ID", procInvoiceId);
            processMap.put("PROC_CUSTVEHICLE_ID", procCustVehicleId);

            mapper.transferReplica(processMap);

            map.put("errorSpcCode", "0");
            map.put("errorSpcMessage", "OK");      
            
        } else {

            mapper.transferProcess(processMap);

            
            getProcInvoiceId = mapper.getProcInvoiceId(dto);
            getAssetId = mapper.getProcAssetId(dto);
            listProcCustVehicleId = mapper.getProcCustVehicleId(dto);
            listProcInvoiceId.add(getProcInvoiceId);
            listProcAssetId.add(getAssetId);
            procInvoiceId = listProcInvoiceId.toArray(new String[listProcInvoiceId.size()]);
            procAssetId = listProcAssetId.toArray(new String[listProcAssetId.size()]);
            String[] procCustVehicleId = listProcCustVehicleId.toArray(new String[listProcCustVehicleId.size()]);
            processMap.put("PROC_INVOICE_ID", procInvoiceId);
            processMap.put("PROC_ASSET_ID", procAssetId);
            processMap.put("PROC_CUSTVEHICLE_ID", procCustVehicleId);

            mapper.transferReplica(processMap);

            map.put("errorSpcCode", "0");
            map.put("errorSpcMessage", "OK");      

        }
        

        return map;
    } 
}



