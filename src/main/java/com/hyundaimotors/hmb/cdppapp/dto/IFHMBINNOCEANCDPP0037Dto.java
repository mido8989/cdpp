package com.hyundaimotors.hmb.cdppapp.dto;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfAutoVehicle;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactHobby;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactSoccerTeam;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactSocialMedia;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.UpdateContactOutput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0037Dto {
    private int rowId;
    private String cnheXpirationDate;
    private String contactId;
    private String suppressAllEmails;
    private String suppressAllSMS;
    private String printedCard;
    private String processAccountRowId;
    private String processContactRowId;

    List<ListOfAutoVehicle> listOfAutoVehicle;
    List<ListOfContactHobby> listOfContactHobby;
    List<ListOfContactSoccerTeam> listOfContactSoccerTeam;
    List<ListOfContactSocialMedia> listOfContactSocialMedia;
    UpdateContactOutput UpdateContactOutput;
}
