package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0006;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBSAPEAICDPP0006Payload {
    
    @Schema(name = "IFHMBSAPEAICDPP0006Payload.Request")
    @Getter
    @Setter
    public static class Request{
        Action action;
        List<ActionNote> actionNote;
        List<ActionProducts> actionProducts;
        List<SynergyActionAudit> synergyActionAudit;
    }

    @Schema(name = "IFHMBSAPEAICDPP0006Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "Connex Action Id", example = "1-40O0EX4")
        private String actionId;
        @Schema(description = "Connex error code. Code 0 means success.", example = "0")
        private String errorSpcCode;
        @Schema(description = "Connex error message.", example = "OK")
        private String errorSpcMessage;
        
    }
}
