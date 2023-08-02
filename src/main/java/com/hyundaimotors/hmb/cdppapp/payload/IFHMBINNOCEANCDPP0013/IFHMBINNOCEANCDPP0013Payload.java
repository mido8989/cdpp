package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBINNOCEANCDPP0013Payload {
    
    
    @Schema(name = "IFHMBINNOCEANCDPP0013Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "External system unique identifier.", example = "B05AM99999")
        private String integrationId;
        @Schema(description = "Company document number (numbers only)", example = "78753880000164")
        private String cnpj;
        @Schema(description = "Company name", example = "Motonet Comercio de Veiculos M")
        private String name;
        @Schema(description = "Company nickname", example = "Abr00E3o Reze - Sorocaba")
        private String nickname;
        @Schema(description = "Partner Flag", example = "Y")
        private String partnerFlag;
        @Schema(description = "Reason Status", example = "Fiscal Check")
        private String reasonStatus;
        @Schema(description = "Dealer Type", example = "HMB")
        private String dealerType;
        @Schema(description = "Dealer Code", example = "B05AM23007")
        private String dealerCode;
        @Schema(description = "Location", example = "B05AM13005")
        private String location;
        @Schema(description = "Main phone.", example = "551530349000")
        private String mainPhone;
        @Schema(description = "Main Fax phone.", example = "551530349001")
        private String fax;
        @Schema(description = "Company email address.", example = "carlos.spinosa@abraoreze.com.br")
        private String mainEmailAddress;
        @Schema(description = "Work Email Address", example = "contatohb@abraoreze.com.br")
        private String workEmailAddress;
        @Schema(description = "Sales Dealer Flag", example = "Y")
        private String salesDealerFlag;
        @Schema(description = "Service Dealer Flag", example = "N")
        private String serviceDealerFlag;
        @Schema(description = "Partner Start Date", example = "41192")
        private String partnerStartDate;
        @Schema(description = "Partner End Date", example = "42287")
        private String partnerEndDate;
        @Schema(description = "Region", example = "R5")
        private String region;
        @Schema(description = "Working Hour", example = "8")
        private String workingHour;
        @Schema(description = "Facebook Page", example = "Prueba Hyundai")
        private String facebookPage;
        @Schema(description = "URL ", example = "www.prueba.com.br")
        private String homepage;
        List<IFHMBINNOCEANCDPP0013Adress> addressList;
        List<IFHMBINNOCEANCDPP0013Contact> contactList;
    }

    @Schema(name = "IFHMBINNOCEANCDPP0013Payload.Response")
    @Getter
    @Setter
    public static class Response{
        private String ErrorSpcCode;
        private String ErrorSpcMessage;

    }
}
