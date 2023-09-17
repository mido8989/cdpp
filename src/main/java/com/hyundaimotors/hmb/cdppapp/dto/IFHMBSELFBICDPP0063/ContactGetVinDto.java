package com.hyundaimotors.hmb.cdppapp.dto.IFHMBSELFBICDPP0063;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactGetVinDto {
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
