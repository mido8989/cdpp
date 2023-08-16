package com.hyundaimotors.hmb.cdppapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBINNOCEANCDPP0018Payload {
    
    @Schema(name = "IFHMBINNOCEANCDPP0018Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "Customer document (CPF).", example = "30311079800")
        private String cpf;
        @Schema(description = "Name of requester system.", example = "Source")
        private String Source;

    }

    @Schema(name = "IFHMBINNOCEANCDPP0018Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "C00F3digo de erro (em caso de falha no servi 00E7o)", example = "0")
        private String errorSpcCode;
        @Schema(description = "Detalhe da mensagem de erro (em caso de falha no servi00E7o)", example = "OK")
        private String errorSpcMessage;

    }
    @Schema(name = "IFHMBINNOCEANCDPP0018Payload.ListOfServices")
    @Getter
    @Setter
    public static class ListOfServices{
        private String protocolNum;
        private String protocolStatus;
        private String serviceType;
        private String serviceArrivaldate;
        private String serviceStartdate;
        private String serviceDuration;
        private String serviceDeliverydate;
        private String serviceStatus;
        private String dealerCode;
        private String dealerName;
        private String vehiclePlate;
        private String vehicleModel;
        private String vehicleModelyear;
        private String vehicleManufacyear;
        private String vehicleExtcolor;
        private String vehicleChassi;
        private String consultantName;
        private String technicianName;
    }
}
