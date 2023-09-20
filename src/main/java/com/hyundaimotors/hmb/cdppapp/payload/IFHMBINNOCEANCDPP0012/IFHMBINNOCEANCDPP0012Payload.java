package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0012;

import java.util.List;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class IFHMBINNOCEANCDPP0012Payload {
    
    @Schema(name = "IFHMBINNOCEANCDPP0012Payload.Request")
    @Getter
    @Setter
    public static class Request{
        
        @Schema(description = "Name of requester system.", example = "Sap")
        private String source;
    }

    @Schema(name = "IFHMBINNOCEANCDPP0012Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "Connex error code. Code 0 means success.", example = "0")
        private String errorSpcCode;
        @Schema(description = "Connex error message.", example = "OK")
        private String errorSpcMessage;

        private List<ChannelPartner> channelPartner;
    }
}
