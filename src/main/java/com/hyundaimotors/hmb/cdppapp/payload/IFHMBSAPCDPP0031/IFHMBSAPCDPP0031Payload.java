package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPCDPP0031;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBSAPCDPP0031Payload {
    
    @Schema(name = "IFHMBSAPCDPP0031Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "Date_Input is null,Informe Data", example = "2023-09-25")
        private String dateInput;
        @Schema(description = "Created Opcion value : scheduled, Created", example = "scheduled")
        private String createdOption;

    }

    @Schema(name = "IFHMBSAPCDPP0031Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "Connex error code. Code 0 means success.", example = "0")
        private String errorSpcCode;
        @Schema(description = "Connex error message.", example = "OK")
        private String errorSpcMessage;
        private List<SearchServiceRequest> listOfHmbSearchServiceRequest;
    }
}
