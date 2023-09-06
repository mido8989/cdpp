package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0038;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Interaction")
@Getter
@Setter
public class Interaction {
    @Schema(description = "Numero Interaction", example = "2")
    private String interactionNum;
    @Schema(description = "Description Interaction", example = "04/10/2018 - O cliente fez o testdrive")
    private String description;
    @Schema(description = "Interaction Date", example = "2018-04-10")
    private String date;
    @Schema(description = "Contact Flag", example = "Y")
    private String contactCustomer;
}
