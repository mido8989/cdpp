package com.hyundaimotors.hmb.cdppapp.payload;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class IFHMBINNOCEANCDPP0019Payload {
    
    @Schema(name = "IFHMBINNOCEANCDPP0019Payload.Request")
    @Getter
    @Setter
    public static class Request{
        private String dealerCode;
        private String year;
        private String month;
        private String source;

    }

    @Schema(name = "IFHMBINNOCEANCDPP0019Payload.Response")
    @Getter
    @Setter
    @SuperBuilder
    public static class Response{
        private String errorSpcCode;
        private String errorSpcMessage;
        List<ListOfDays> listOfDays;
    }

    @Schema(name = "ListOfDays")
    @Getter
    @Setter
    public static class ListOfDays{
        private String description;
        private String available;
    }
}
