package com.hyundaimotors.hmb.cdppapp.payload.AccountReverse;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class AccountReversePayLoad {
    @Schema(name = "AccountPayLoad.Request")
    @Getter
    @Setter
    public static class Request{

    }

    @Schema(name = "AccountPayLoad.Response")
    @Getter
    @Setter
    public static class Response{
        @JsonProperty("Error_spcCode")
        private String errorSpcCode;
        @Schema(description = "errorSpcMessage", example = "OK")
        @JsonProperty("Error_spcMessage")
        private String errorSpcMessage;
    }
}
