package com.hyundaimotors.hmb.cdppapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBSAPEAICDPP0035Payload {
    
    @Schema(name = "IFHMBSAPEAICDPP0035Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "URL", example = "C:/windows")        
        private String url;
        @Schema(description = "SR Number", example = "HMB8739974017")   
        private String srNumber;        
    }
    
    @Schema(name = "IFHMBSAPEAICDPP0035Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "Attachment Id", example = "1-U1VO")   
        private String attachmentId;
        @Schema(description = "Connex error code. Code 0 means success.", example = "0")   
        private String errorSpcCode;
        @Schema(description = "Connex error message.", example = "OK")   
        private String errorSpcMessage;      
    }
}
