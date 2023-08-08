package com.hyundaimotors.hmb.cdppapp.dto;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.AutoVehicleWebserviceWFAccountPayload;
import com.hyundaimotors.hmb.cdppapp.payload.AutoVehicleWebserviceWFContactPayload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBSAPEAICDPP0010Dto {
        private String rowId;
        private String parConId;
        private String parAccId;
        private String intergrationId;
        private String status;
        private String serialNumber;
        private String renavamCode;
        private String engineNumber;
        private String vehicleLicenseNumber;
        private String fscOcn;
        private String modelName;
        private String internalColorCode;
        private String externalColorCode;
        private String fuel;
        private String purchasePrice;
        private String manufactureYear;
        private String mileage;
        private String saleToDealerDate;
        private String warrantyStartDate;
        private String billingDate;
        private String deliverToCustomerDate;
        private String sellingDealerCode;
        private String negotiationType;
        private String subscriptionType;
        private String fiscalReceipt;
        private String salesrepId;
        private String salesrepFirstName;
        private String salesrepLastName;
        private String errorSpcCode;
        private String errorSpcMessage;
        private String connexContactId;
        private String contactType;
        private String contactStatus;
        private String employee;
        private String connexAccountId;
        private String accountType;
        private String accountStatus;
        List<AutoVehicleWebserviceWFContactPayload> contact;
        List<AutoVehicleWebserviceWFAccountPayload> account;
}
