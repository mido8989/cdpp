package com.hyundaimotors.hmb.cdppapp.payload.IFHMBZICARDCDPP0032;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "SearchContactTD")
@Getter
@Setter
public class SearchContactTD {
    @JsonProperty("ContactId")
    private String contactId;
    @JsonProperty("FirstName")
    private String firstName;
    @JsonProperty("LastName")
    private String lastName;
    @JsonProperty("CPF")
    private String cpf;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("BirthDate")
    private String birthDate;
    @JsonProperty("CellularPhone")
    private String cellularPhone;
    @JsonProperty("HomePhone")
    private String homePhone;
    @JsonProperty("EmailAddress")
    private String emailAddress;
    @JsonProperty("WorkPhone")
    private String workPhone;
    @JsonProperty("RowId")
    private String rowId;
    @JsonProperty("AmountFriends")
    private String amountFriends;
    @JsonProperty("AmountChildren")
    private String amountChildren;
    @JsonProperty("CNHExpirationDate")
    private String cnhexpirationDate;
    @JsonProperty("LoyaltyActivationDate")
    private String loyaltyActivationDate;
    @JsonProperty("LoyaltyExpirationDate")
    private String loyaltyExpirationDate;
    @JsonProperty("LoyaltyEngagementIndex")
    private String loyaltyEngagementIndex;
    @JsonProperty("LoyaltySatisfactionLevel")
    private String loyaltySatisfactionLevel;
    @JsonProperty("LoyaltyCRMIndex")
    private String loyaltyCRMIndex;
    @JsonProperty("LoyaltyLifeCycle")
    private String loyaltyLifeCycle;
    @JsonProperty("LoyaltyTierLevel")
    private String loyaltyTierLevel;
    @JsonProperty("LoyaltyStatus")
    private String loyaltyStatus;
    @JsonProperty("LoyaltyOPTINFla")
    private String loyaltyOPTINFla;
    @JsonProperty("HMBPrintedCard")
    private String hmbPrintedCard;
    @JsonProperty("SuppressAllEmails")
    private String suppressAllEmails;
    @JsonProperty("SuppressAllSMS")
    private String suppressAllSMS;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("street")
    private String street;
    @JsonProperty("number")
    private String number;
    @JsonProperty("complement")
    private String complement;
    @JsonProperty("county")
    private String county;
    @JsonProperty("City")
    private String city;
    @JsonProperty("State")
    private String State;
}
