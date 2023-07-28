package com.hyundaimotors.hmb.cdppapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBINNOCEANCDPP0027Payload {
    
    @Schema(name = "IFHMBINNOCEANCDPP0027Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "Contact Id", example = "1-U1VO")
        private String contactId;
        @Schema(description = "Name of external system.", example = "Source")
        private String appSource;
        
    }

    @Schema(name = "IFHMBINNOCEANCDPP0027Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "Connex error code. Code 0 means success.", example = "0")
        private String errorSpcCode;
        @Schema(description = "Connex error message.", example = "OK")
        private String errorSpcMessage;
        @Schema(description = "Contact Id", example = "1-U1VO")
        private String contactId;
        @Schema(description = "Loyalty Status", example = "Active")
        private String loyaltyStatus;
    }
}
