package com.hyundaimotors.hmb.cdppapp.dto;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBMAPFRECDPP0009.IFHMBMAPFRECDPP0009RSAServicePayload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBMAPFRECDPP0009Dto {
    private String claimId;
    private String source;
    private String requesterFirstName;
    private String requesterLastName;
    private String requesterDocument;
    private String requesterRelationship;
    private String createDate;
    private String reason;
    private String breakdownDescription;
    private String breakdownCode;
    private String breakdownNature;
    private String claimStatus;
    private String serialNumber;
    private String effectiveStartDate;
    private String effectiveEndDate;
    private String exchangeDate;
    private String vehiclePlate;
    private String vehicleMileage;
    List<IFHMBMAPFRECDPP0009RSAServicePayload> rsaSetvice;
}