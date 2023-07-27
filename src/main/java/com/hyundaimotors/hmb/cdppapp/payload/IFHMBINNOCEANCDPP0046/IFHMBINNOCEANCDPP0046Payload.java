package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0046;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBINNOCEANCDPP0046Payload {
    
    @Schema(name = "IFHMBINNOCEANCDPP0046Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "Dealer Code", example = "B05AM99999")
        private String dealerCode;
        @Schema(description = "Name of requester system.", example = "Source")
        private String Source;
        private List<ListOfServices> listOfServices;

    }

    @Schema(name = "IFHMBINNOCEANCDPP0046Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "Return the same dealer-code passed as input parameter for this web service, for redundancy verification purposes in the other end.", example = "B05AM99999")
        private String dealerCode;
        @Schema(description = "Returns 0 when success.", example = "0")
        private String errorSpcCode;
        @Schema(description = "Returns OK when success.", example = "OK")
        private String errorSpcMessage;
    }
}
