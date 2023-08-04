package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0021;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBINNOCEANCDPP0021Payload {
    
    @Schema(name = "IFHMBINNOCEANCDPP0021Payload.Request")
    @Getter
    @Setter
    public static class Request{
        private String serviceName;
        private String Source;
    }

    @Schema(name = "IFHMBINNOCEANCDPP0021Payload.Response")
    @Getter
    @Setter
    public static class Response{
        private String ErrorSpcCode;
        private String ErrorSpcMessage;
        private String serviceName;
        List<ListOfDealers> listOfDealers;
    }
    
    
}
