package com.hyundaimotors.hmb.cdppapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class IFHMBZICARDCDPP0007Payload {

    @Schema(name = "IFHMBZICARDCDPP0007Payload.Request")
    @Getter
    @Setter
    public static class Request{

        @NotNull
        @Schema(description = "Contact Asset Id", example = "1-1SJ-92")
        private String assetId;

        @NotNull
        @Schema(description = "Contact Id", example = "1-6T3P3")
        private String contactId;

        @NotNull
        @Schema(description = "RelationShip Contact/asset", example = "Owner")
        private String relationShip;

        @Schema(description = "Retail Date", example = "2012-12-13")
        private String hkmeRetailDate;

    }

    @Schema(name = "IFHMBZICARDCDPP0007Payload.Response")
    @Getter
    @Setter
    public static class Response{

        @Schema(description = "errorSpcCode", example = "0")
        private String errorSpcCode;

        @Schema(description = "errorSpcMessage", example = "OK")
        private String errorSpcMessage;
        
        @Schema(description = "Number Recors", example = "100")
        private String numAffRow;

    }
    
}
