package com.hyundaimotors.hmb.cdppapp.payload.AccountReverse;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class AccountReversePayLoad {
    @Schema(name = "AccountPayLoad.Request")
    @Getter
    @Setter
    public static class Request{
        private String personMailingStreet;
        private String billingStreet;
        private String houseBuildingNo;
        private String complement;
        private String neighborhood;
        private String personMailingCity;
        private String billingCountry;
        private String billingCity;
        private String personMailingState;
        private String billingState;
        private String personMailingPostalCode;
        private String billingPostalCode;
        private String personMailingLatitude;
        private String billingLatitude;
        private String personMailingLongitude;
        private String billingLongitude;
        private String type;
        private String firstName;
        private String lastName;
        private String personEmail;
        private String personHomePhone;
        private String workPhone;
        private String personMobilePhone;
        private String gender;
        private String pcd;
        private String income;
        private String productOfInterest;
        private String literacy;
        private String calledBy;
        private String employee;
        private String dealerCode;
        private String crmIndex;
        private String engagementIndex;
        private String optInFlag;
        private String satisficationLevel;
        private String cnhexpirationDate;
        private String loyaltyActivationDate;
        private String loyaltyStatus;
        private String cpf;
        private String receiveProductNewsFlag;
        private String receiveRetailOffersFlag;
        private String receiveServiceOffersFlag;
        private String receiveNewsLetterFlag;
        private String receiveEventsFlag;
        private String receiveResearchFlag;
        private String blockedMobile;
        private String blockedVideoCall;
        private String blockedCalls;
        private String blockedletters;
        private String blockedEmails;
        private String blockedSms;
        private String blockedWhatsapp;
        private String preferredContactChannel;
        private String maritalStatus;
        private String lifeCycle;
        private String salutation;
        private String personBirthdate;
        private String influentialPerson;
        private String registrySource;
        private String loyaltyValue;
        private String occupation;
        private String cnpj;
        private String name;
        private String nickname;
        private String accountCategory;
        private String contractRenewalDate;
        private String phone;
        private String fax;
        private String emailAddress;
        private String website;
        private String situationStatusReason;
        private String region;
        private String businessType;
        private String industry;
        private String fleetSize;
        private String organizationType;
        private String dealershopType;
        private String salesFlag;
        private String serviceFlag;
        private String businessHours;
        private String facebook;
        private String fleetAdministrator;
        private String description;
        private String leadManager;
        private String dms;
        private String apikey;
        private String whatsappPhone;
        private String situationStatus;
        private String partnerType;
        private String numberOfEmployees;
        private String operationDate;
        private String terminationDate;
        private String customerSap;
        private String legacyCreatedDate;
        private String integratedId;
        private String recordTypeId;
        private String sfId;
    }

    @Schema(name = "AccountPayLoad.Response")
    @Getter
    @Setter
    public static class Response{
        private String rowId;
        @JsonProperty("Error_spcCode")
        private String errorSpcCode;
        @Schema(description = "errorSpcMessage", example = "OK")
        @JsonProperty("Error_spcMessage")
        private String errorSpcMessage;
    }
}
