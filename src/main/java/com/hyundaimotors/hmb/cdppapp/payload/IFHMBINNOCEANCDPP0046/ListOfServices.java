package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0046;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListOfServices {
    @Schema(description = "Service type code.", example = "1st maintenance")
    private String service_type;
    @Schema(description = "Service length in minutes.", example = "60")
    private int service_length;
}
