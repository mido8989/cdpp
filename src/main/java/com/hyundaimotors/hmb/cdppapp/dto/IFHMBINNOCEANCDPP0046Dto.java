package com.hyundaimotors.hmb.cdppapp.dto;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0046.ListOfServices;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0046Dto {
    private String dealerCode;
    private String Source;
    private String parRowId;
    private List<ListOfServices> listOfServices;
    private String errorSpcCode;
    private String errorSpcMessage;
}

