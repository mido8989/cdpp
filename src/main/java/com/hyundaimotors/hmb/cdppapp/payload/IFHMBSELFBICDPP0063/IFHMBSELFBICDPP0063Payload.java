package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSELFBICDPP0063;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBSELFBICDPP0063Payload {
    
    @Schema(name = "IFHMBSELFBICDPP0063Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "MM/DD/YYYY, billing date to capture.", example = "2020-10-25")
        private String dateInput;
        @Schema(description = "Y or N. If you select Y, then Siebel will get all VIN searching them by Created Field. If you select N, then Siebel will get all VIN searching them by Sales date field.", example = "Y")
        private String createdOption;
        @Schema(description = "1=All Sales, 2=Different from Direct Sales, 3=Direct Sales", example = "1")
        private String seq;
        @Schema(description = "Vehicle number", example = "9BHBG51CADP010537")
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
