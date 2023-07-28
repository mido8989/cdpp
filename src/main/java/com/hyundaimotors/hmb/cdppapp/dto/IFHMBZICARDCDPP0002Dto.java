package com.hyundaimotors.hmb.cdppapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBZICARDCDPP0002Dto {
    private String row_id;
    private String integrationid;
    private String cpf;
    private String firstname;
    private String lastname;
    private String birthdate;
    private String gender;
    private String source;
    private String homephone;
    private String workphone;
    private String cellphone;
    private String emailaddress;
    private String wayofcontact;
    private String occupation;
    private String maritalstatus;
    private String income;
    private String carofinterest;
    private String calledby;
    private String literacy;
    private String behavioralsegment;
    private String valuesegment;
    private String googlesegment;
    private String facebooksegment;
    private String lifecyclesegment;
    private String interestsegment;
    private String contactoptin;
    private String streetaddress;
    private String streetaddress2;
    private String streetaddress3;
    private String county;
    private String city;
    private String state;
    private String postalcode;
    private String suppressallcalls;
    private String suppressallmailings;
    private String suppressallemails;
    private String suppressallsms;
    private String printedcard;
    private String already_customer;
    private String ContactId;
    private String Error_spcCode;
    private String Error_spcMessage;
}
