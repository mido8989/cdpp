package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

public class IFHMBINNOCEANCDPP0037Payload {
    
    @Schema(name = "IFHMBINNOCEANCDPP0037Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "CNH Expiration Date", example = "2022-05-20")
        private String cnheXpirationDate;
        @Schema(description = "Contact Id", example = "1-7890WX")
        private String contactId;
        @Schema(description = "Flag to define if customer wants all emails suppressed.", example = "Y")
        private String suppressAllEmails;
        @Schema(description = "Flag to define if customer wants all sms suppressed.", example = "Y")
        private String suppressAllSMS;
        @Schema(description = "Printed card flag. Valid values: true, false", example = "Y")
        private String printedCard;


        List<ListOfAutoVehicle> listOfAutoVehicle;
        List<ListOfContactHobby> listOfContactHobby;
        List<ListOfContactSoccerTeam> listOfContactSoccerTeam;
        List<ListOfContactSocialMedia> listOfContactSocialMedia;

    }

    @Schema(name = "IFHMBINNOCEANCDPP0037Payload.Response")
    @Getter
    @Setter
    public static class Response{
        private String contactId;
        private String error_spcCode;
        private String error_spcMessage;

        UpdateContactOutput UpdateContactOutput = new UpdateContactOutput();
        List<ListOfAutoVehicle> listOfAutoVehicle;
        List<ListOfContactHobby> listOfContactHobby;
        List<ListOfContactSoccerTeam> listOfContactSoccerTeam;
        List<ListOfContactSocialMedia> listOfContactSocialMedia;

    }
}
