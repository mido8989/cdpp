package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "ListOfAutoVehicle")
@Getter
@Setter
public class ListOfAutoVehicle {
    @Schema(description = "Asset Id", example = "1-1DL-53")
    private String vehicleId;
    @Schema(description = "Relashionship Status", example = "Owner")
    private String relashionshipStatus;
    @Schema(description = "Renavam Code", example = "497269414")
    private String HMBRenavamCode;
    @Schema(description = "Curren Mileage", example = "24350.0")
    private String currenMileage;
    @Schema(description = "Vehicle License Number", example = "FGZ 4544")
    private String vehicleLicenseNumber;

}
