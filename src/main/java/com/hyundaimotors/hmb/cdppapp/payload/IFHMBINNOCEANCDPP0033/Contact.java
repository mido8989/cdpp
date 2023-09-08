package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0033;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Contact")
@Getter
@Setter
public class Contact {
    private String id;
    private String integrationId;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String document;
    private String sex;
    private String personType;
    private String postalCode;
    private String street;
    private String number;
    private String complement;
    private String county;
    private String city;
    private String state;
    private String homePhone;
    private String workPhone;
    private String cellPhone;
    private String emailAddress;
    private String relationshipType;
    private String relationshipStatus;
}
