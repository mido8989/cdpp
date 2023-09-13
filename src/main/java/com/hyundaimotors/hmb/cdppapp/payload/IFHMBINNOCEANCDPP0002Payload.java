package com.hyundaimotors.hmb.cdppapp.payload;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class IFHMBINNOCEANCDPP0002Payload {
    
    @Schema(name = "IFHMBINNOCEANCDPP0002Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "External system unique identifier.", example = "1-7890WX")
        private String integrationId;
        @Schema(description = "Individual's document number (numbers only)", example = "12337343006")
        private String cpf;
        @NotNull
        @Schema(description = "Contact first name", example = "Jose")
        private String firstName;
        @NotNull
        @Schema(description = "Contact last name", example = "Ciclano")
        private String lastName;
        @Schema(description = "Contact birth date - Format: MM/DD/YYYY", example = "2005-11-24")
        private String birthDate;
        @Schema(description = "Contact gender. Valid values: Uninformed, Female, Male", example = "Male")
        private String gender;
        @Schema(description = "External source. Valid values: Call Center, Dealer, Home Page, Marketing, Sap ", example = "Call Center")
        private String source;
        @Schema(description = "Contact home phone.", example = "2345678")
        private String homePhone;
        @Schema(description = "Contact work phone.", example = "3456789")
        private String workPhone;
        @Schema(description = "Contact cell phone.", example = "11941319501")
        private String cellPhone;
        @NotNull
        @Schema(description = "Contact email address.", example = "kilosolution2s@gmail.com")
        private String emailAddress;
        @Schema(description = "Contact preferred way of contact. Valid values: Uninformed, Email, Mail, Sms, Home phone, Mobile phone, Work phone ", example = "Email")
        private String wayOfContact;
        @Schema(description = "Contact occupation. Valid values: Uninformed", example = "Uniinformed")
        private String occupation;
        @Schema(description = "Contact preferred way of contact. Valid values: Uninformed, Divorced, Married, Partner, Separated, Single, Stable union, Widowed, Others", example = "Single")
        private String maritalStatus;
        @Schema(description = "Contact's income.", example = "R$1.000 or less")
        private String income;
        @Schema(description = "Contact's car of interest. Valid values: HB20, HB20 FOR YOU, HB20 Spicy, HB20 World Cup, HB20S", example = "HB20")
        private String carOfInterest;
        @Schema(description = "The way contact prefer to be called.", example = "Complete primaty")
        private String calledBy;
        @Schema(description = "Contact literacy. Valid values: Uninformed, Illiterate, Complete primary", example = "Uniinformed")
        private String literacy;
        @Schema(description = "Behavioral Segment. Valid values: Uninformed", example = "Uniinformed")
        private String behavioralSegment;
        @Schema(description = "Value Segment. Valid values: Uninformed", example = "Uniinformed")
        private String valueSegment;
        @Schema(description = "Google Segment. Valid values: ", example = "Uniinformed")
        private String googleSegment;
        @Schema(description = "Facebook Segment. Valid values: ", example = "Uniinformed")
        private String facebookSegment;
        @Schema(description = "Life Cycle Segment. Valid values: Uninformed", example = "Uniinformed")
        private String lifeCycleSegment;
        @Schema(description = "Interest Segment. Valid values: Uninformed", example = "Uniinformed")
        private String interestSegment;
        @Schema(description = "Opt in defines if contact wants to receive news about retail and services. Valid values: Y, N", example = "Y")
        private String contactOptIn;
        @Schema(description = "Address street name.", example = "Rua Tutoia")
        private String streetAddress;
        @Schema(description = "Address house number", example = "380")
        private String streetAddress2;
        @Schema(description = "Address complement.", example = "Andar 3")
        private String streetAddress3;
        @Schema(description = "Address county.", example = "Paraiso")
        private String county;
        @Schema(description = "Address city.", example = "Brasil")
        private String city;
        @Schema(description = "Address state.", example = "S\u00E3o Paulo ")
        private String state;
        @Schema(description = "Address zip code.", example = "4007001")
        private String postalCode;
        @Schema(description = "Flag to define if customer wants all calls suppressed.", example = "Y")
        private String suppressAllCalls;
        @Schema(description = "Flag to define if customer wants all mailing suppressed.", example = "Y")
        private String suppressAllMailings;
        @Schema(description = "Flag to define if customer wants all emails suppressed.", example = "Y")
        private String suppressAllEmails;
        @Schema(description = "Flag to define if customer wants all sms suppressed.", example = "Y")
        private String suppressAllSMS;
        @Schema(description = "Printed card flag. Valid values: true, false", example = "Y")
        private String printedCard;
        @Schema(description = "Flag to define if customer is already a customer. Valid values: Y, N", example = "N")
        private String alreadyCustomer;
        List<InboundContactWorkflowCarPayLoad> car;
        List<InboundContactWorkflowAppPayLoad> app;
        List<InboundContactWorkflowSnsPayLoad> socialMedia;
        List<InboundContactWorkflowHobbyPayLoad> hobby;
        List<InboundContactWorkflowSoccerTeamPayLoad> soccerTeam;
    }

    @Schema(name = "IFHMBINNOCEANCDPP0002Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "Contact Identifier.")
        private String ContactId;
        @Schema(description = "Connex error code. Code 200 means success.", example = "200")
        private String Error_spcCode;
        @Schema(description = "Connex error message.", example = "success")
        private String Error_spcMessage;
    }
}
