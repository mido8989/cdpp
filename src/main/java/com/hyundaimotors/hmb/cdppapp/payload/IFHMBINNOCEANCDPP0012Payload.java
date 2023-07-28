package com.hyundaimotors.hmb.cdppapp.payload;

import java.util.List;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class IFHMBINNOCEANCDPP0012Payload {
    
    @Schema(name = "IFHMBINNOCEANCDPP0012Payload.Request")
    @Getter
    @Setter
    public static class Request{
        
        @Schema(description = "Name of requester system.", example = "0000063997")
        private String source;
        @Schema(description = "Connex error code. Code 0 means success.", example = "0")
        private String errorSpcCode;
        @Schema(description = "Connex error message.", example = "OK")
        private String errorSpcMessage;
    }

    @Schema(name = "IFHMBINNOCEANCDPP0012Payload.Response")
    @Getter
    @Setter
    public static class Response{
        private ChannelPartner channelPartner;
        List<ChannelPartnerContact> channelPartnerContact;
    }

    @Schema(name = "IFHMBINNOCEANCDPP0012Payload.channelPartner")
    @Getter
    @Setter
    public static class ChannelPartner{
        @Schema(description = "External system unique identifier.", example = "B05AM99999")
        private String integrationId;
        @Schema(description = "Company document number (numbers only)", example = "78753880000164")
        private String cnpj;
        @Schema(description = "Company name", example = "Motonet Comercio de Veiculos M")
        private String name;
        @Schema(description = "Company nickname", example = "Abr 00E3o Reze - Sorocaba")
        private String nickname;
        @Schema(description = "Account Status", example = "Active")
        private String accountStatus;
        @Schema(description = "Reason Status", example = "Fiscal Check")
        private String reasonStatus;
        @Schema(description = "Dealer Type", example = "Dealer")
        private String dealerType;
        @Schema(description = "Dealer Code", example = "B05AM99999")
        private String dealerCode;
        @Schema(description = "Main phone.", example = "2345678")
        private String mainPhone;
        @Schema(description = "Main Fax phone.", example = "3456789")
        private String fax;
        @Schema(description = "Company email address.", example = "kilosolution2s@gmail.com")
        private String mainEmailAddress;
        @Schema(description = "Work Email Address", example = "Prueba@hyundai.com")
        private String workEmailAddress;
        @Schema(description = "Sales Dealer Flag", example = "Y")
        private String salesDealerFlag;
        @Schema(description = "Service Dealer Flag", example = "N")
        private String serviceDealerFlag;
        @Schema(description = "Partner Start Date", example = "2012-10-10")
        private String partnerStartDate;
        @Schema(description = "Partner End Date", example = "2015-10-10")
        private String partnerEndDate;
        @Schema(description = "Region", example = "Sul")
        private String region;
        @Schema(description = "Working Hour", example = "8")
        private String workingHour;
        @Schema(description = "Facebook Page", example = "Prueba Hyundai")
        private String facebookPage;
        @Schema(description = "URL ", example = "www.prueba.com.br")
        private String homepage;
        @Schema(description = "Address street name.", example = "Rua Tutoia")
        private String streetAddress;
        @Schema(description = "Address house number", example = "380")
        private String streetAddress2;
        @Schema(description = "Address complement.", example = "Andar 3")
        private String streetAddress3;
        @Schema(description = "Address city.", example = "Brasil")
        private String city;
        @Schema(description = "Address state.", example = "S00E3o Paulo ")
        private String state;
        @Schema(description = "Address Neighborhood", example = "Paraiso")
        private String neighborhood;
        @Schema(description = "Address Latitude", example = "23.497694")
        private String latitude;
        @Schema(description = "Address Longitude", example = "47.441679")
        private String longitude;
        @Schema(description = "Address zip code.", example = "4007001")
        private String zipCode;
    }

    @Schema(name = "IFHMBINNOCEANCDPP0012Payload.ChannelPartnerContact")
    @Getter
    @Setter
    public static class ChannelPartnerContact{
        @Schema(description = "External system unique identifier.", example = "1-7890WX")
        private String integrationId;
        @Schema(description = "Individual's document number (numbers only)", example = "12337343006")
        private String cpf;
        @Schema(description = "Contact first name", example = "Jose")
        private String firstName;
        @Schema(description = "Contact last name", example = "Ciclano")
        private String lastName;
        @Schema(description = "Contact email address.", example = "kilosolution2s@gmail.com")
        private String emailAddress;
        @Schema(description = "Contact home phone.", example = "2345678")
        private String homePhone;
        @Schema(description = "Contact work phone.", example = "3456789")
        private String workPhone;
        @Schema(description = "Contact cell phone.", example = "11941319501")
        private String cellPhone;
        @Schema(description = "Contact job Title", example = "Uniinformed")
        private String jobTitle;
        @Schema(description = "Dealer Contact Status,  HMBDealerContactStatusonDealer", example = "Active")
        private String status;
        @Schema(description = "Dealer Contact Note,  HMBDealerContactNotesonDealer", example = "30694501808")
        private String notes;

    }
}
