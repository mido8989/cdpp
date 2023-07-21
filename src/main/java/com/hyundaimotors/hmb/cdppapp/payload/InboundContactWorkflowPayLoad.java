package com.hyundaimotors.hmb.cdppapp.payload;

import java.util.List;

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
        @JsonProperty(value="BirthDate")
        private String BirthDate;
        @JsonProperty(value="Gender")
        private String Gender;
        @JsonProperty(value="Source")
        private String Source;
        @JsonProperty(value="HomePhone")
        private String HomePhone;
        @JsonProperty(value="WorkPhone")
        private String WorkPhone;
        @JsonProperty(value="CellPhone")
        private String CellPhone;
        @JsonProperty(value="EmailAddress")
        private String EmailAddress;
        @JsonProperty(value="WayOfContact")
        private String WayOfContact;
        @JsonProperty(value="Occupation")
        private String Occupation;
        @JsonProperty(value="MaritalStatus")
        private String MaritalStatus;
        @JsonProperty(value="Income")
        private String Income;
        @JsonProperty(value="CarOfInterest")
        private String CarOfInterest;
        @JsonProperty(value="CalledBy")
        private String CalledBy;
        @JsonProperty(value="Literacy")
        private String Literacy;
        @JsonProperty(value="BehavioralSegment")
        private String BehavioralSegment;
        @JsonProperty(value="ValueSegment")
        private String ValueSegment;
        @JsonProperty(value="GoogleSegment")
        private String GoogleSegment;
        @JsonProperty(value="FacebookSegment")
        private String FacebookSegment;
        @JsonProperty(value="LifeCycleSegment")
        private String LifeCycleSegment;
        @JsonProperty(value="InterestSegment")
        private String InterestSegment;
        @JsonProperty(value="ContactOptIn")
        private String ContactOptIn;
        @JsonProperty(value="StreetAddress")
        private String StreetAddress;
        @JsonProperty(value="StreetAddress2")
        private String StreetAddress2;
        @JsonProperty(value="StreetAddress3")
        private String StreetAddress3;
        @JsonProperty(value="County")
        private String County;
        @JsonProperty(value="City")
        private String City;
        @JsonProperty(value="State")
        private String State;
        @JsonProperty(value="PostalCode")
        private String PostalCode;
        @JsonProperty(value="SuppressAllCalls")
        private String SuppressAllCalls;
        @JsonProperty(value="SuppressAllMailings")
        private String SuppressAllMailings;
        @JsonProperty(value="SuppressAllEmails")
        private String SuppressAllEmails;
        @JsonProperty(value="SuppressAllSMS")
        private String SuppressAllSMS;
        @JsonProperty(value="PrintedCard")
        private String PrintedCard;
        @JsonProperty(value="already_customer")
        private String already_customer;
        @JsonProperty(value="ListOfCurrentCars")
        private List<InboundContactWorkflowCarPayLoad> ListOfCurrentCars;
        @JsonProperty(value="ListOfApps")
        private List<InboundContactWorkflowAppPayLoad> ListOfApps;
        @JsonProperty(value="ListOfSocialMedia")
        private List<InboundContactWorkflowSnsPayLoad> ListOfSocialMedia;
        @JsonProperty(value="ListOfHobby")
        private List<InboundContactWorkflowHobbyPayLoad> ListOfHobby;
        @JsonProperty(value="ListOfSoccerTeam")
        private List<InboundContactWorkflowSoccerTeamPayLoad> ListOfSoccerTeam;
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
