package com.hyundaimotors.hmb.cdppapp.dto.IFHMBSELFBICDPP0063;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSELFBICDPP0063.Vehicle;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBSELFBICDPP0063Dto {
    private String dateInput;
    private String createdOption;
    private String seq;
    private String vin;
    private String errorSpcCode;
    private String errorSpcMessage;
    List<VehicleDto> ListOfVehicle;
}
