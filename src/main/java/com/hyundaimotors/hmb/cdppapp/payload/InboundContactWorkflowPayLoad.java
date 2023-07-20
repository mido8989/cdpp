package com.hyundaimotors.hmb.cdppapp.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class InboundContactWorkflowPayLoad {
    
    @Schema(name = "InboundContactWorkflowPayLoad.Request")
    @Getter
    @Setter
    public static class Request{
        @JsonProperty(value="IntegrationId")
        private String IntegrationId;
        @JsonProperty(value="CPF")
        private String CPF;
        @JsonProperty(value="FirstName")
        private String FirstName;
        @JsonProperty(value="LastName")
        private String LastName;
        // private String BirthDate;
        // private String Gender;
        // private String Source;
        // private String HomePhone;
        // private String WorkPhone;
        // private String CellPhone;
        // private String EmailAddress;
        // private String WayOfContact;
        // private String Occupation;
        // private String MaritalStatus;
        // private String Income;
        // private String CarOfInterest;
        // private String CalledBy;
        // private String Literacy;
        // private String BehavioralSegment;
        // private String ValueSegment;
        // private String GoogleSegment;
        // private String FacebookSegment;
        // private String LifeCycleSegment;
        // private String InterestSegment;
        // private String ContactOptIn;
        // private String StreetAddress;
        // private String StreetAddress2;
        // private String StreetAddress3;
        // private String County;
        // private String City;
        // private String State;
        // private String PostalCode;
        // private String SuppressAllCalls;
        // private String SuppressAllMailings;
        // private String SuppressAllEmails;
        // private String SuppressAllSMS;
        // private String PrintedCard;
        // private String already_customer;

    }
    
    @Schema(name = "InboundContactWorkflowPayLoad.Response")
    @Getter
    @Setter
    public static class Response{
        private String ContactId;
        private String Error_spcCode;
        private String Error_spcMessage;
    }
}
