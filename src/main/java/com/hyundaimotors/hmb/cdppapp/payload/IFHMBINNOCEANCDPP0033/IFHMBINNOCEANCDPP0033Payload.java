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
        private String idInterface;
        private String source;
        private String serialNumber;
        private String licenseNumber;
        private String renavamCode;
        private String cpf;
        private String cnpj;
        private String startDate;
        private String endDate;
        private String connexid;
    }

    @Schema(name = "IFHMBINNOCEANCDPP0033Payload.Response")
    @Getter
    @Setter
    public static class Response{
        private String ErrorSpcCode;
        private String ErrorSpcMessage;
        List<HMBVehicle> listOfHMBVehicle;

    }
}
