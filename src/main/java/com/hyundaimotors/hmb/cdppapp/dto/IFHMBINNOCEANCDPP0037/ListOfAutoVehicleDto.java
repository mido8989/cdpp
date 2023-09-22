package com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0037;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListOfAutoVehicleDto {
    private int rowId;
    private String vehicleId;
    private String relationshipStatus;
    private String hmbRenavamCode;
    private int currentMileage;
    private String vehicleLicenseNumber;
}
