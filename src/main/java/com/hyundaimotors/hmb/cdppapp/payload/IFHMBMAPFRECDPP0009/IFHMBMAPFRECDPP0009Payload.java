package com.hyundaimotors.hmb.cdppapp.payload.IFHMBMAPFRECDPP0009;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBMAPFRECDPP0009Payload {
    
    @Schema(name = "IFHMBMAPFRECDPP0009Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "RSA Integration Id", example = "MONDIAL29130301")
        private String claimId;
        @Schema(description = "Source", example = "Telephone")
        private String source;
        @Schema(description = "RSA Requester First Name", example = "BIBIANA")
        private String requesterFirstName;
        @Schema(description = "RSA Requester Last Name", example = "CESARIO DE OLIVEIRA MORENO")
        private String requesterLastName;
        @Schema(description = "RSA Requester Document", example = "28728923898")
        private String requesterDocument;
        @Schema(description = "RSA Requester Vehicle Rel", example = "Driver")
        private String requesterRelationship;
        @Schema(description = "RSA Claim Date", example = "2015-08-06")
        private String createDate;
        @Schema(description = "RSA Reason", example = "PANE")
        private String reason;
        @Schema(description = "RSA Breakdown Description", example = "PANE")
        private String breakdownDescription;
        @Schema(description = "RSA Breakdown Code", example = "31")
        private String breakdownCode;
        @Schema(description = "RSA Breakdown Nature", example = "PANE")
        private String breakdownNature;
        @Schema(description = "RSA Status", example = "INTERVEN 00C7 00C3O")
        private String claimStatus;
        @Schema(description = "Auto Vehicle Serial Number", example = "9BHBH41DBGP633658")
        private String serialNumber;
        @Schema(description = "RSA Effective Start Date", example = "2015-08-06")
        private String effectiveStartDate;
        @Schema(description = "RSA Effective End Date", example = "2015-10-06")
        private String effectiveEndDate;
        @Schema(description = "RSA Exchange Date", example = "2015-08-06")
        private String exchangeDate;
        @Schema(description = "Vehicle License Number", example = "FCK1760")
        private String vehiclePlate;
        @Schema(description = "RSA Mileage", example = "900")
        private String vehicleMileage;
        List<IFHMBMAPFRECDPP0009RSAServicePayload> rsaSetvice;
    }

    @Schema(name = "IFHMBMAPFRECDPP0009Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "Protocol Numer", example = "HMB8761468242")
        private String protocol;
        @Schema(description = "Connex error code. Code 0 means success.", example = "0")
        private String errorSpcCode;
        @Schema(description = "Connex error message.", example = "OK")
        private String errorSpcMessage;

    }
}
