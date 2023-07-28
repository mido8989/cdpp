package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0046;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListOfServices {
    @Schema(description = "Dealer Code", example = "1st maintenance")
    private String serviceType;
    @Schema(description = "Dealer Code", example = "90")
    private int serviceLength;
}
