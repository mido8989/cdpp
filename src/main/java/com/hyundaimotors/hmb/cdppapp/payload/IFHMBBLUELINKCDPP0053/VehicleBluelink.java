package com.hyundaimotors.hmb.cdppapp.payload.IFHMBBLUELINKCDPP0053;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "VehicleBluelink")
@Getter
@Setter
public class VehicleBluelink {
    @Schema(description = "This tag was deactivated. serialNumber should be used instead.", example = "9BHBG51DADP011617")
    private String chassi;
    List<Bluelink> ListOfBluelink;
}
