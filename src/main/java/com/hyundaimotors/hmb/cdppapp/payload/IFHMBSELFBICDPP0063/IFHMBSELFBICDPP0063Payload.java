package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSELFBICDPP0063;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class IFHMBSELFBICDPP0063Payload {
    
    @Schema(name = "IFHMBSELFBICDPP0063Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @NotNull
        @Schema(description = "MM/DD/YYYY, billing date to capture.", example = "2018-10-30")
        private String dateInput;
        @NotNull
        @Schema(description = "Y or N. If you select Y, then Siebel will get all VIN searching them by Created Field. If you select N, then Siebel will get all VIN searching them by Sales date field.", example = "Y")
        private String createdOption;
        @NotNull
        @Schema(description = "1=All Sales, 2=Different from Direct Sales, 3=Direct Sales", example = "1")
        private String seq;
        @Schema(description = "Vehicle number", example = "9BHGA811BKP099943")
        private String vin;
    }

    @Schema(name = "IFHMBSELFBICDPP0063Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "errorSpcCode", example = "0")
        private String errorSpcCode;
        @Schema(description = "errorSpcMessage", example = "OK")
        private String errorSpcMessage;
        List<Vehicle> ListOfVehicle;
    }
}
