package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0024;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class IFHMBINNOCEANCDPP0024Payload {
    
    @Schema(name = "IFHMBINNOCEANCDPP0024Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @NotNull
        @Schema(description = "UPSERT", example = "UPSERT")
        private String operation;
        @Schema(description = "Record identifier.", example = "1")
        private int excpId;
        @NotNull
        @Schema(description = "Dealer Code", example = "30311079800")
        private String employeeCPF;
        @NotNull
        @Schema(description = "Dealer code where the consultant will be available.", example = "B05AM99999")
        private String dealerCode;
        @Schema(description = "Exception's start date.Format: MM/DD/YYYY", example = "2016-10-12")
        private String excpStartDate;
        @Schema(description = "Exception's end date.Format: MM/DD/YYYY", example = "2016-12-12")
        private String excpEndDate;
        @Schema(description = "Absence period flag. Possible values are:", example = "N")
        private String absencePeriod;
        @Schema(description = "First working period start time.Format: HH:MM:SS", example = "2017-11-15 11:00:00 AM")
        private String period1Start;
        @Schema(description = "First working period end time. Format: HH:MM:SS", example = "2017-11-15 12:00:00 PM")
        private String period1End;
        @Schema(description = "Second working period start time.Format: HH:MM:SS", example = "2017-11-15 13:00:00 PM")
        private String period2Start;
        @Schema(description = "Second working period end time.Format: HH:MM:SS", example = "2017-11-15 15:00:00 PM")
        private String period2End;
        @Schema(description = "Third working period start time.Format: HH:MM:SS", example = "2017-11-15 16:00:00 PM")
        private String period3Start;
        @Schema(description = "Third working period end time.Format: HH:MM:SS", example = "2017-11-15 20:00:00 PM")
        private String period3End;
        @Schema(description = "Exception's description.", example = "description")
        private String excpDesc;
        @Schema(description = "Name of requester system.", example = "Source")
        private String source;
    }

    @Schema(name = "IFHMBINNOCEANCDPP0024Payload.Response")
    @Getter
    @Setter
    public static class Response{
        private String errorSpcCode;
        private String errorSpcMessage;
    }
}
