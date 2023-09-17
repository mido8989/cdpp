package com.hyundaimotors.hmb.cdppapp.dto.IFHMBSELFBICDPP0063;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDto {
    private String Chassi;
    private String SalesDate;
    private String SalesDealerCode;
    private String SalesDealerNick;
    private String SalesCode;
    private String SalesVersion;
    private String SalesFSCOCN;
    private String SalesMotor;
    private String VINModel;
    private String VINColor;
    private String Updated;
    private String DelHome;
    private String DigitalSales;
    private String TDHome;
    private String SalesCancelled;
    private String assetId;
    List<ContactGetVinDto> ListOfContact;
    List<AccountGetVinDto> ListOfAccount;
}
