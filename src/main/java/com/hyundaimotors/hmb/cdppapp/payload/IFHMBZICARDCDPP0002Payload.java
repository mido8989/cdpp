package com.hyundaimotors.hmb.cdppapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBZICARDCDPP0002Payload {
    
    @Schema(name = "IFHMBZICARDCDPP0002Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "External system unique identifier.", example = "1-7890WX")
        private String integrationid;
        @Schema(description = "Individual's document number (numbers only)", example = "12337343006")
        private String cpf;
        @Schema(description = "Contact first name", example = "Jose")
        private String firstname;
        @Schema(description = "Contact last name", example = "Ciclano")
        private String lastname;
        @Schema(description = "Contact birth date - Format: MM/DD/YYYY", example = "2005-11-24")
        private String birthdate;
        @Schema(description = "Contact gender. Valid values: Uninformed, Female, Male", example = "Male")
        private String gender;
        @Schema(description = "External source. Valid values: Call Center, Dealer, Home Page, Marketing, Sap ", example = "Call Center")
        private String source;
        @Schema(description = "Contact home phone.", example = "2345678")
        private String homephone;
        @Schema(description = "Contact work phone.", example = "3456789")
        private String workphone;
        @Schema(description = "Contact cell phone.", example = "11941319501")
        private String cellphone;
        @Schema(description = "Contact email address.", example = "kilosolution2s@gmail.com")
        private String emailaddress;
        @Schema(description = "Contact preferred way of contact. Valid values: Uninformed, Email, Mail, Sms, Home phone, Mobile phone, Work phone ", example = "Email")
        private String wayofcontact;
        @Schema(description = "Contact occupation. Valid values: Uninformed", example = "Uniinformed")
        private String occupation;
        @Schema(description = "Contact preferred way of contact. Valid values: Uninformed, Divorced, Married, Partner, Separated, Single, Stable union, Widowed, Others", example = "Single")
        private String maritalstatus;
        @Schema(description = "Contact's income.", example = "R$1.000 or less")
        private String income;
        @Schema(description = "Contact's car of interest. Valid values: HB20, HB20 FOR YOU, HB20 Spicy, HB20 World Cup, HB20S", example = "HB20")
        private String carofinterest;
        @Schema(description = "The way contact prefer to be called.", example = "Complete primaty")
        private String calledby;
        @Schema(description = "Contact literacy. Valid values: Uninformed, Illiterate, Complete primary", example = "Uniinformed")
        private String literacy;
        @Schema(description = "Behavioral Segment. Valid values: Uninformed", example = "Uniinformed")
        private String behavioralsegment;
        @Schema(description = "Value Segment. Valid values: Uninformed", example = "Uniinformed")
        private String valuesegment;
        @Schema(description = "Google Segment. Valid values: ", example = "Uniinformed")
        private String googlesegment;
        @Schema(description = "Facebook Segment. Valid values: ", example = "Uniinformed")
        private String facebooksegment;
        @Schema(description = "Life Cycle Segment. Valid values: Uninformed", example = "Uniinformed")
        private String lifecyclesegment;
        @Schema(description = "Interest Segment. Valid values: Uninformed", example = "Uniinformed")
        private String interestsegment;
        @Schema(description = "Opt in defines if contact wants to receive news about retail and services. Valid values: Y, N", example = "Y")
        private String contactoptin;
        @Schema(description = "Address street name.", example = "Rua Tutoia")
        private String streetaddress;
        @Schema(description = "Address house number", example = "380")
        private String streetaddress2;
        @Schema(description = "Address complement.", example = "Andar 3")
        private String streetaddress3;
        @Schema(description = "Address county.", example = "Paraiso")
        private String county;
        @Schema(description = "Address city.", example = "Brasil")
        private String city;
        @Schema(description = "Address state.", example = "S\u00E3o Paulo ")
        private String state;
        @Schema(description = "Address zip code.", example = "4007001")
        private String postalcode;
        @Schema(description = "Flag to define if customer wants all calls suppressed.", example = "Y")
        private String suppressallcalls;
        @Schema(description = "Flag to define if customer wants all mailing suppressed.", example = "Y")
        private String suppressallmailings;
        @Schema(description = "Flag to define if customer wants all emails suppressed.", example = "Y")
        private String suppressallemails;
        @Schema(description = "Flag to define if customer wants all sms suppressed.", example = "Y")
        private String suppressallsms;
        @Schema(description = "Printed card flag. Valid values: true, false", example = "True")
        private String printedcard;
        @Schema(description = "Flag to define if customer is already a customer. Valid values: Y, N", example = "N")
        private String already_customer;
    }

    @Schema(name = "IFHMBZICARDCDPP0002Payload.Response")
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
