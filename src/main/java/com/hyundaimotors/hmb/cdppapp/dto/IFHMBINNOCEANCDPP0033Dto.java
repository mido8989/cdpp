package com.hyundaimotors.hmb.cdppapp.dto;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0033.HMBVehicle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0033Dto {
    private String idInterface;
    private String source;
    private String serialNumber;
    private String licenseNumber;
    private String renavamCode;
    private String cpf;
    private String cnpj;
    private String startDate;
    private String endDate;
    private String connexid;
    private String ErrorSpcCode;
    private String ErrorSpcMessage;
    List<HMBVehicle> listOfHMBVehicle;
}
