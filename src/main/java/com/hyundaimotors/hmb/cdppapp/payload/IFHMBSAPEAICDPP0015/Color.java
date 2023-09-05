package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0015;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Color")
@Getter
@Setter
public class Color {
    @NotNull
    @Schema(description = "Type", example = "AUTO_EXTERIOR")
    private String type;
    @Schema(description = "LIC", example = "RAR")
    private String code;
    @Schema(description = "Value and Language = ENU", example = "TROPIC RED")
    private String descriptionEM;
    @NotNull
    @Schema(description = "Value and Language = PTB", example = "VERMELHO TROPICAL")
    private String descriptionPT;
    @Schema(description = "Low", example = "S")
    private String colorType;
    @Schema(description = "High", example = "Solid")
    private String colorDescription;

}
