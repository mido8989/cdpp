package com.hyundaimotors.hmb.cdppapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBZICARDCDPP0002Payload {
    
    @Schema(name = "IFHMBZICARDCDPP0002Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "External system unique identifier.")
        private String integrationid;
        @Schema(description = "Individual's document number (numbers only)")
        private String cpf;
        @Schema(description = "Contact first name")
        private String firstname;
        @Schema(description = "Contact last name")
        private String lastname;
        @Schema(description = "Contact birth date - Format: MM/DD/YYYY")
        private String birthdate;
        @Schema(description = "Contact gender. Valid values: Uninformed, Female, Male")
        private String gender;
        @Schema(description = "External source. Valid values: Call Center, Dealer, Home Page, Marketing, Sap ")
        private String source;
        @Schema(description = "Contact home phone.")
        private String homephone;
        @Schema(description = "Contact work phone.")
        private String workphone;
        @Schema(description = "Contact cell phone.")
        private String cellphone;
        @Schema(description = "Contact email address.")
        private String emailaddress;
        @Schema(description = "Contact preferred way of contact. Valid values: Uninformed, Email, Mail, Sms, Home phone, Mobile phone, Work phone ")
        private String wayofcontact;
        @Schema(description = "Contact occupation. Valid values: Uninformed")
        private String occupation;
        @Schema(description = "Contact preferred way of contact. Valid values: Uninformed, Divorced, Married, Partner, Separated, Single, Stable union, Widowed, Others")
        private String maritalstatus;
        @Schema(description = "Contact's income.")
        private String income;
        @Schema(description = "Contact's car of interest. Valid values: HB20, HB20 FOR YOU, HB20 Spicy, HB20 World Cup, HB20S")
        private String carofinterest;
        @Schema(description = "The way contact prefer to be called.")
        private String calledby;
        @Schema(description = "Contact literacy. Valid values: Uninformed, Illiterate, Complete primary")
        private String literacy;
        @Schema(description = "Behavioral Segment. Valid values: Uninformed")
        private String behavioralsegment;
        @Schema(description = "Value Segment. Valid values: Uninformed")
        private String valuesegment;
        @Schema(description = "Google Segment. Valid values: ")
        private String googlesegment;
        @Schema(description = "Facebook Segment. Valid values: ")
        private String facebooksegment;
        @Schema(description = "Life Cycle Segment. Valid values: Uninformed")
        private String lifecyclesegment;
        @Schema(description = "Interest Segment. Valid values: Uninformed")
        private String interestsegment;
        @Schema(description = "Opt in defines if contact wants to receive news about retail and services. Valid values: Y, N")
        private String contactoptin;
        @Schema(description = "Address street name.")
        private String streetaddress;
        @Schema(description = "Address house number")
        private String streetaddress2;
        @Schema(description = "Address complement.")
        private String streetaddress3;
        @Schema(description = "Address county.")
        private String county;
        @Schema(description = "Address city.")
        private String city;
        @Schema(description = "Address state.")
        private String state;
        @Schema(description = "Address zip code.")
        private String postalcode;
        @Schema(description = "Flag to define if customer wants all calls suppressed.")
        private String suppressallcalls;
        @Schema(description = "Flag to define if customer wants all mailing suppressed.")
        private String suppressallmailings;
        @Schema(description = "Flag to define if customer wants all emails suppressed.")
        private String suppressallemails;
        @Schema(description = "Flag to define if customer wants all sms suppressed.")
        private String suppressallsms;
        @Schema(description = "Printed card flag. Valid values: true, false")
        private String printedcard;
        @Schema(description = "Flag to define if customer is already a customer. Valid values: Y, N")
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
