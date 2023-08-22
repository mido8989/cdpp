package com.hyundaimotors.hmb.cdppapp.dto;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0023.IFHMBINNOCEANCDPP0023Day;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0023Dto {
    private int rowId;
    private String source;
    private String employeeCPF;
    private String dealerCode;
    List<IFHMBINNOCEANCDPP0023Day>listOfDays;
    private String errorSpcCode;
    private String errorSpcMessage;
}
