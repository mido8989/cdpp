package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0026;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "ListOfProduct")
@Getter
@Setter
public class ListOfProduct {
    @Schema(description = "FSC OCN unique code. This is the key field for integration.", example = "1-RGHT2")
    private String name;
    @Schema(description = "Name of external system.", example = "Source")
    private String source;
    @Schema(description = "Connex product type.", example = "Transportation")
    private String type;
    @Schema(description = "If informed, adds a new entry and expires existing entries in the price list HMB Suggested Public Price., HMB Vehicle Suggested Price", example = "50000")
    private String suggestedPrice;
}