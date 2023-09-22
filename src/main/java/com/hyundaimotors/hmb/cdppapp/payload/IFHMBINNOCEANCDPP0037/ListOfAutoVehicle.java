package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "ListOfAutoVehicle")
@Getter
@Setter
public class ListOfAutoVehicle {
    @Schema(description = "Asset Id", example = "1-1DL-53")
    @NotNull
    private String vehicleId;
    @Schema(description = "Relationship Status", example = "Owner")
    private String relationshipStatus;
    @Schema(description = "Renavam Code", example = "497269414")
    private String hmbRenavamCode;
    @Schema(description = "Curren Mileage", example = "24350.0")
    private int currentMileage;
    @Schema(description = "Vehicle License Number", example = "FGZ 4544")
    private String vehicleLicenseNumber;

}
