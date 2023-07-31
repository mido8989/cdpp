package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "UpdateContactOutput")
@Getter
@Setter
public class UpdateContactOutput {
        private String cnheXpirationDate;
        private String contactId;
        private String firstName;
        private String lastName;
        private String birthDate;
        private String gender;
        private String retailDate;
        private String homePhone;
        private String workPhone;
        private String cellPhone;
        private String emailAddress;
        private String amountFriends;
        private String amountChildren;
        private String loyaltyOPTINFla;
        private String streetAddress;
        private String streetAddress2;
        private String streetAddress3;
        private String county;
        private String city;
        private String state;
        private String postalCode;
        private String suppressAllEmails;
        private String suppressAllSMS;
        private String printedCard;
}
