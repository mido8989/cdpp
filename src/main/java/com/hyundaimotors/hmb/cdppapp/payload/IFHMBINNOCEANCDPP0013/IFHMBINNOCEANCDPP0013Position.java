package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "IFHMBINNOCEANCDPP0013Position")
@Getter
@Setter
public class IFHMBINNOCEANCDPP0013Position {
    @Schema(description = "Position", example = "HMB Administrator")
    private String position;
}
