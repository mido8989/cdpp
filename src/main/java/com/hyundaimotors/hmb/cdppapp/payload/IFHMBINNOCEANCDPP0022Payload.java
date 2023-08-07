package com.hyundaimotors.hmb.cdppapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class IFHMBINNOCEANCDPP0022Payload {
    
    @Schema(name = "IFHMBINNOCEANCDPP0022Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "Name of requester system.", example = "Source")
        private String source;
        @Schema(description = "Holiday description.", example = "Proclama 00E7 00E3o da Rep 00FAblica")
        private String holidayDescription;
        @Schema(description = "Holiday date. Format: MM/DD/YYYY", example = "2016-11-15")
        private String holidayDate;
        @Schema(description = "Dealer Code", example = "B05AM99999")
        private String dealerCode;
        @Schema(description = "Requested operation. Possible values are: UPSERT for update or insert. DELETE for deletion.", example = "DELETE")
        private String operation;
    }

    @Schema(name = "IFHMBINNOCEANCDPP0022Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "Returns 0 when success.", example = "0")
        private String ErrorSpcCode;
        @Schema(description = "Returns OK when success.", example = "OK")
        private String ErrorSpcMessage;
    }
}
