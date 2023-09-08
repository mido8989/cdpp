package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0033;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "HMBVehicle")
@Getter
@Setter
public class HMBVehicle {
    private String assetId;
    private String vin;
    private String engineNumber;
    private String licenseNumber;
    private String renavamCode;
    private String fscOcn;
    private String description;
    private String manufactureYear;
    private String modelYear;
    private String vehicleLicenseNumber;
    private String internalColorCode;
    private String externalColorCode;
    private String fuel;
    private String status;
    private String saleToDealerDate;
    private String billingDate;
    private String ccsFlg;
    private String warrantyStartDate;
    private String fiscalReceipt;
    private String dealerId;
    private String dealerCode;
    private String dealerName;
    private String dealerNickname;
    private String salesManName;
    List<Contact> listOfContact;
    List<Account> listOfAccount;
}
