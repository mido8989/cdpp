package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0033;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBINNOCEANCDPP0033Payload {
    @Schema(name = "IFHMBINNOCEANCDPP0033Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "Literal: 1~12", example = "1")
        private String idInterface;
        @Schema(description = "Requester system.", example = "SAP")
        private String source;
        @Schema(description = "Chassis number.", example = "9BHBG51CAKP082334")
        private String serialNumber;
        @Schema(description = "Vehicle plate number.", example = "RHL6I87")
        private String licenseNumber;
        @Schema(description = "Vehicle national register number (renavam code).", example = "01060963970")
        private String renavamCode;
        @Schema(description = "Individual Id (cpf).", example = "39609003842")
        private String cpf;
        @Schema(description = "Legal entity registration number (cnpj).", example = "07550674000109")
        private String cnpj;
        @Schema(description = "Billing start date for search., Format: MM/DD/YYYY", example = "2015-09-28")
        private String startDate;
        @Schema(description = "Billing End date for search., Format: MM/DD/YYYY", example = " 2015-05-28")
        private String endDate;
        @Schema(description = "Connex Asset Id.", example = "1-383PB4S")
        private String connexid;
    }

    @Schema(name = "IFHMBINNOCEANCDPP0033Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "Connex error code. Code 0 means success.", example = "0")
        private String ErrorSpcCode;
        @Schema(description = "Connex error message.", example = "OK")
        private String ErrorSpcMessage;
        List<HMBVehicle> listOfHMBVehicle;

    }
}
