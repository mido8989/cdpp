package com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0023;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0023.EmpAvailabIn;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0023.IFHMBINNOCEANCDPP0023Day;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0023Dto {
    EmpAvailabInDto EmpAvailabIn;
    private int dayRowId;
    private String errorSpcCode;
    private String errorSpcMessage;
}
