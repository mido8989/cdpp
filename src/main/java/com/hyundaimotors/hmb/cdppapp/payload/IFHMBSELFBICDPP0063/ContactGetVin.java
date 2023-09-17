package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSELFBICDPP0063;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Contact")
@Getter
@Setter
public class ContactGetVin {
    private String IdSiebel;
    private String RelatedType;
    private String RelatedStatus;
    private String SuppressAllCalls;
    private String SuppressAllEmails;
    private String SuppressAllMailings;
    private String SuppressAllMobilePhone;
    private String SuppressAllSMS;
    private String SuppressAllVideoCall;
    private String SuppressAllWhatsapp;
}
