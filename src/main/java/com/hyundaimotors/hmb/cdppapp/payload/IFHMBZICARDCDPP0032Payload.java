package com.hyundaimotors.hmb.cdppapp.payload;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class IFHMBZICARDCDPP0032Payload {
    
    @Schema(name = "IFHMBZICARDCDPP0032Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "cpfNumber", example = "36422039816")
        private String cpf;
        @Schema(description = "ContactId", example = "1-18G0N")
        private String contactId;
    }

    @Schema(name = "IFHMBZICARDCDPP0032Payload.Response")
    @Getter
    @Setter
    @SuperBuilder
    public static class Response{
        private GetContactTD getContactTD;
        private SearchContactTD searchContactTD;
        private List<ListOfServiceRequest> listOfServiceRequest;
    }
    
    @Schema(name = "IFHMBZICARDCDPP0032Payload.GetContactTD")
    @Getter
    @Setter
    public static class GetContactTD{
        private String ErrorSpcCode;
        private String ErrorSpcMessage;

    }
    
    @Schema(name = "IFHMBZICARDCDPP0032Payload.SearchContactTD")
    @Getter
    @Setter
    public static class SearchContactTD{
        private String contactId;
        private String firstName;
        private String lastName;
        private String cpf;
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
    
    }

    @Schema(name = "IFHMBZICARDCDPP0032Payload.ListOfServiceRequest")
    @Getter
    @Setter
    public static class ListOfServiceRequest{
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
}
