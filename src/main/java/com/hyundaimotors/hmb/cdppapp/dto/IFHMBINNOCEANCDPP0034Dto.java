package com.hyundaimotors.hmb.cdppapp.dto;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0034.Accessory;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0034.Part;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0034.ServiceRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0034Dto {
    private int rowId;
    ServiceRequest serviceRequest;
    List<Accessory> listOfAccessories;
    List<Part> listOfParts;
    private String errorSpcCode;
    private String errorSpcMessage;
}
