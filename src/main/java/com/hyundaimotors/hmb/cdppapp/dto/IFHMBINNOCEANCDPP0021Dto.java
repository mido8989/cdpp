package com.hyundaimotors.hmb.cdppapp.dto;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0021.ListOfDealers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0021Dto {
    private String serviceName;
    private String Source;

    private String ErrorSpcCode;
    private String ErrorSpcMessage;
    
    List<ListOfDealers> listOfDealers;
}
