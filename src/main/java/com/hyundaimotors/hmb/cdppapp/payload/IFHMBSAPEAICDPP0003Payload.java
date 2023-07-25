package com.hyundaimotors.hmb.cdppapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBSAPEAICDPP0003Payload {
    
    @Schema(name = "IFHMBSAPEAICDPP0003Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "FSC OCN unique code. This is the key field for integration.", example = "1-RGHT2")
        private String name;
        @Schema(description = "Name of external system.", example = "")
        private String source;
        @Schema(description = "Connex product type. It must be a literal value for the purpose of this project: Vehicle.", example = "Vehicle")
        private String type;
        @Schema(description = "Vehicle description: E.g.:", example = "HB20 1.6L COMFORT PLUS MT")
        private String part;
        @Schema(description = "Vehicle advanced description. E.g.:", example = "Ar-condicionado")
        private String advdesc; 
        @Schema(description = "Vehicle model year.", example = "2010")
        private int modelYear;
        @Schema(description = "Vehicle chassi model.", example = "Hatchback")
        private String chassiModel; 
        @Schema(description = "Vehicle body style.", example = " 998cc")
        private String bodyStyle;
        @Schema(description = "Vehicle engine capacity description.", example = " DOHC")
        private String hmbCylindercapacity;
        @Schema(description = "Vehicle engine type description. ", example = "MPI")
        private String engine;
        @Schema(description = "Vehicle engine friendly description.", example = "1.0 Turbo")
        private String engineDesc;
        @Schema(description = "Vehicle version friendly description. ", example = "Comfort Plus")
        private String versionDesc; 
        @Schema(description = "Vehicle transmission friendly description. ", example = "Manual 6 marchas")
        private String transmissionDesc;
        @Schema(description = "Vehicle transmission. ", example = "A.M")
        private String transmission;
        @Schema(description = "Vehicle fuel type description.", example = "Flex")
        private String fuel;
        @Schema(description = "Vehicle door amount. ", example = "5")
        private int doorsAmnt;
        @Schema(description = "Vehicle suggested price", example = "50000")
        private String suggestedPrice;
    }    

    @Schema(name = "IFHMBSAPEAICDPP0003Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "Connex error code. Code 200 means success.", example = "200")
        private String errorSpcCode;
        @Schema(description = "Connex error message.", example = "success")
        private String errorSpcMessage;
    }
}
