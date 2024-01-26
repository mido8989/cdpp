package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSELFBICDPP0062;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class IFHMBSELFBICDPP0062Payload {
    
    @Schema(name = "IFHMBSELFBICDPP0062Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "Date of leads to capture. YYYY-MM-DD", example = "2021-01-01")
        @NotNull @NotBlank @NotEmpty
        private String date;
        @Schema(description = "Y or N. If you select Y, then Siebel will retrieve all leads searching them by Created Field. If you select N, then Siebel will retrieve all leads searching them by Updated field.", example = "Y")
        @NotNull @NotBlank @NotEmpty
        private String created;
        @Schema(description = "“All”,“RequestaQuote”,\"TestDrive\" ,\"LoyaltyProgram\" ,\"HyundaiBank\" ,\"Showroom\" ,\"Source_Massivas\" ,\"Source_Others\" ,\"Payment\"", example = "RequestaQuote")
        @NotNull @NotBlank @NotEmpty
        private String voc;
        @Schema(description = "protocal", example = "HMB20740368284")
        private String lead;
        
        public void setCreated(String created) {
            
            if(created != null && !created.trim().isEmpty() && created.trim().toUpperCase().startsWith("Y")){
                this.created = "Y";
            }else{
                this.created = "N";
            }
        }
    }

    @Schema(name = "IFHMBSELFBICDPP0062Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "errorSpcCode", example = "0")
        @JsonProperty("Error_spcCode")
        private String errorSpcCode;
        @Schema(description = "errorSpcMessage", example = "OK")
        @JsonProperty("Error_spcMessage")
        private String errorSpcMessage;
        @JsonProperty("Lead")
        List<TotalLead> totallead;
    }
}
