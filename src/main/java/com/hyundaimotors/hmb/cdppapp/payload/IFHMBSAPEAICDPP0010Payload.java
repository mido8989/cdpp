package com.hyundaimotors.hmb.cdppapp.payload;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


public class IFHMBSAPEAICDPP0010Payload {
    
    @Schema(name = "IFHMBSAPEAICDPP0010Payload.Request")
    @Getter
    @Setter
    public static class request{
        @Schema(description = "This tag was deactivated. serialNumber should be used instead.", example = "9BHBG51DADP011617")
        @NotNull
        private String intergrationId;
        @Schema(description = "Valid values:Dealer Owned, Customer Owned, Cancelled.", example = "Dealer")
        private String status;
        @Schema(description = "Chassis number.Unique code for the asset record to be added. It is used as key field for this integration, to determine if a existing record should be updated or a new record should be inserted.", example = "9BHBH41DBGP633658")
        @NotNull
        private String serialNumber;
        @Schema(description = "Renavam code.", example = "497269414")
        private String renavamCode;
        @Schema(description = "Engine number.", example = "F3LACU001831")
        private String engineNumber;
        @Schema(description = "Vehicle Plate.", example = "FCK1760")
        private String vehicleLicenseNumber;
        @Schema(description = "Product FSC OCN Name", example = "092214A000")
        @NotNull
        private String fscOcn;
        @Schema(description = "Product description.", example = "")
        private String modelName;
        @Schema(description = "Internal color code.", example = "9P")
        private String internalColorCode;
        @Schema(description = "External color code.", example = "R5S")
        private String externalColorCode;
        @Schema(description = "SAP fuel type.", example = "F")
        private String fuel;
        @Schema(description = "Purchase price.", example = "50000")
        private String purchasePrice;
        @Schema(description = "Manufacture year.", example = "2012")
        private String manufactureYear;
        @Schema(description = "Mileage.", example = "1000")
        private String mileage;
        @Schema(description = "Sale to dealer date. Format: MM/DD/YYYY", example = "2012.10.25")
        private String saleToDealerDate;
        @Schema(description = "Warranty's start date. Format: MM/DD/YYYY", example = "2012.10.16")
        private String warrantyStartDate;
        @Schema(description = "Billing date. Format: MM/DD/YYYY", example = "2012.10.16")
        private String billingDate;
        @Schema(description = "Deliver to customer date. Format: MM/DD/YYYY", example = "2012.11.6")
        private String deliverToCustomerDate;
        @Schema(description = "Dealer code.", example = "B05AM19029")
        private String sellingDealerCode;
        @Schema(description = "Negotiation type.", example = "ZV01")
        private String negotiationType;
        @Schema(description = "Subscription type.", example = "KU")
        private String subscriptionType;
        @Schema(description = "Fiscal receipt.", example = "")
        private String fiscalReceipt;
        @Schema(description = "Integration Id (CPF) for the sales representative.", example = "23351")
        private String salesrepId;
        @Schema(description = "First name for the sales representative.", example = "Carlos")
        private String salesrepFirstName;
        @Schema(description = "Last name for the sales representative.", example = "Soto")
        private String salesrepLastName;
        List<AutoVehicleWebserviceWFContactPayload> contact;
        List<AutoVehicleWebserviceWFAccountPayload> account;
    }

    @Schema(name = "IFHMBSAPEAICDPP0010Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "Connex error code. Code 0 means success.", example = "0")
        private String Error_spcCode;
        @Schema(description = "Connex error message.", example = "OK")
        private String Error_spcMessage;
    }
}
