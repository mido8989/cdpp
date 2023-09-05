package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0015;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBSAPEAICDPP0015Payload {
    
    @Schema(name = "IFHMBSAPEAICDPP0015Payload.Request")
    @Getter
    @Setter
    public static class Request{
        List<String> source;
        List<Color> listOfColor;
    }

    @Schema(name = "IFHMBSAPEAICDPP0015Payload.Response")
    @Getter
    @Setter
    public static class Response{
        private String ErrorSpcCode;
        private String ErrorSpcMessage;
    }
}
