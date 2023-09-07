package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0038;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Temperature")
@Getter
@Setter
public class Temperature {
    @Schema(description = "Numero de Temperature", example = "1")
    private String temperatureNum;
    @Schema(description = "Valor Temperature", example = "F")
    private String temperature;
}
