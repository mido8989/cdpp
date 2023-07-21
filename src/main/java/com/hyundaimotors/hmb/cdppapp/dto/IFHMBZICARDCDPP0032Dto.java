package com.hyundaimotors.hmb.cdppapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBZICARDCDPP0032Dto {
    private String cpf;
    private String contactId;

    //GetContactTD
    private String ErrorSpcCode;
    private String ErrorSpcMessage;
    
    //SearchContactTD
    private String firstName;
    private String lastName;
    private String genre;
    private String birthDate;
    private String cellularPhone;
    private String homePhone;
    private String emailAddress;
    private String workPhone;
    private String rowId;
    private String amountFriends;
    private String amountChildren;
    private String cnhexpirationDate;
    private String loyaltyActivationDate;
    private String loyaltyExpirationDate;
    private String loyaltyEngagementIndex;
    private String loyaltySatisfactionLevel;
    private String loyaltyCRMIndex;
    private String loyaltyLifeCycle;
    private String loyaltyTierLevel;
    private String loyaltyStatus;
    private String loyaltyOPTINFla;
    private String hmbPrintedCard;
    private String suppressAllEmails;
    private String suppressAllSMS;
    private String postalCode;
    private String street;
    private String number;
    private String complement;
    private String county;
    private String city;
    private String State;

    //ListOfServiceRequest
    private String searchspec;
    private String codigoSAPTD;
    private String protocol;
    private String status;
    private String dealer;
    private String model;
    private String salesRepFirstName;
    private String salesRepLastName;
    private String testDriveDate;
    private String testDriveTime;
    private String testDriveConfirmed;
    private String area;
}
