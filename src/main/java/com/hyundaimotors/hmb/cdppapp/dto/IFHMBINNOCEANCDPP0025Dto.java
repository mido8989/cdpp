package com.hyundaimotors.hmb.cdppapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0025Dto {
    private int rowId;
    private String source;
    private String srNumber;
    private String srReason;
    private String srLevel1;
    private String srLevel2;
    private String srLevel3;
    private String srLevel4;
    private String srSource;
    private String srMethod;
    private String srDescription;
    private String srPriority;
    private String srStatus;
    private String dealerCode;
    private String chassis;
    private String customerCPF;
    private String consultantCPF;
    private String technicianCPF;
    private String serviceType;
    private String serviceStatus;
    private String serviceArrivaldate;
    private String serviceStartdate;
    private int serviceDuration;
    private String serviceDeliverydate;
    private String ErrorSpcCode;
    private String ErrorSpcMessage;
}
