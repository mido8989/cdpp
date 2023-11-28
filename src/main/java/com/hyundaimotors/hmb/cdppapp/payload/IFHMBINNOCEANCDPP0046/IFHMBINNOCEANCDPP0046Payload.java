package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0046;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class IFHMBINNOCEANCDPP0046Payload {
    
    @Schema(name = "IFHMBINNOCEANCDPP0046Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "Dealer Code", example = "B05AM99999")
        @NotNull @NotBlank @NotEmpty
        @JsonProperty("dealer-code")
        @NotNull @NotBlank @NotEmpty
        private String dealerCode;
        @Schema(description = "Name of requester system.", example = "CDPP")
        @NotNull @NotBlank @NotEmpty
        private String source;
        @Valid @JsonProperty("ListOfServices")
        private List<ListOfServices> listOfServices;

    }

    @Schema(name = "IFHMBINNOCEANCDPP0046Payload.Response")
    @Getter
    @Setter
    public static class Response{        
        
        @Schema(description = "Returns 0 when success.", example = "0")
        @JsonProperty("Error_spcCode")
        private String errorSpcCode;
        @Schema(description = "Returns OK when success.", example = "OK")
        @JsonProperty("Error_spcMessage")
        private String errorSpcMessage;

        ManageDealerServiceOut manageDealerServiceOut;
    }
}
