package com.hyundaimotors.hmb.cdppapp.dto;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowAppPayLoad;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowCarPayLoad;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowHobbyPayLoad;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowSnsPayLoad;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowSoccerTeamPayLoad;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0002Dto {
    private int rowId;
    private String integrationId;
    private String cpf;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String gender;
    private String source;
    private String homePhone;
    private String workPhone;
    private String cellPhone;
    private String emailAddress;
    private String wayOfContact;
    private String occupation;
    private String maritalStatus;
    private String income;
    private String carOfInterest;
    private String calledBy;
    private String literacy;
    private String behavioralSegment;
    private String valueSegment;
    private String googleSegment;
    private String facebookSegment;
    private String lifeCycleSegment;
    private String interestSegment;
    private String contactOptIn;
    private String streetAddress;
    private String streetAddress2;
    private String streetAddress3;
    private String county;
    private String city;
    private String state;
    private String postalCode;
    private String suppressAllCalls;
    private String suppressAllMailings;
    private String suppressAllEmails;
    private String suppressAllSMS;
    private String printedCard;
    private String alreadyCustomer;
    private String ContactId;
    private String Error_spcCode;
    private String Error_spcMessage;
    List<InboundContactWorkflowCarPayLoad> ListOfCurrentCars;
    List<InboundContactWorkflowAppPayLoad> ListOfApps;
    List<InboundContactWorkflowSnsPayLoad> ListOfSocialMedia;
    List<InboundContactWorkflowHobbyPayLoad> ListOfHobby;
    List<InboundContactWorkflowSoccerTeamPayLoad> ListOfSoccerTeam;
}
