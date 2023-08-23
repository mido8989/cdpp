package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0023;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBINNOCEANCDPP0023Payload {
    @Schema(name = "IFHMBINNOCEANCDPP0023Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "Name of requester system.", example = "Source")
        private String source;
        @Schema(description = "Employee document number.", example = "30311079800")
        private String employeeCPF;
        @Schema(description = "Dealer Code", example = "B05AM99999")
        private String dealerCode;
        List<IFHMBINNOCEANCDPP0023Day>listOfDays;
    }

    @Schema(name = "IFHMBINNOCEANCDPP0023Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "Returns 0 when success.", example = "0")
        private String errorSpcCode;
        @Schema(description = "Returns OK when success.", example = "OK")
        private String errorSpcMessage;

    }
}