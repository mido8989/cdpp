package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSELFBICDPP0063;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Vehicle")
@Getter
@Setter
public class Vehicle {
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
    @JsonProperty("Contact")
    List<ContactGetVin> ListOfContact;
    @JsonProperty("Account")
    List<AccountGetVin> ListOfAccount;
}
