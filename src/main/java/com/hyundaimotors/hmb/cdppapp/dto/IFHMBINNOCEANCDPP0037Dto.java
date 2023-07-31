package com.hyundaimotors.hmb.cdppapp.dto;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfAutoVehicle;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactHobby;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactSoccerTeam;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactSocialMedia;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.UpdateContactOutput;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0037Dto {
    private String cnheXpirationDate;
    private String contactId;
    private String suppressAllEmails;
    private String suppressAllSMS;
    private String printedCard;

    List<ListOfAutoVehicle> listOfAutoVehicle;
    List<ListOfContactHobby> listOfContactHobby;
    List<ListOfContactSoccerTeam> listOfContactSoccerTeam;
    List<ListOfContactSocialMedia> listOfContactSocialMedia;
    UpdateContactOutput UpdateContactOutput;
}
