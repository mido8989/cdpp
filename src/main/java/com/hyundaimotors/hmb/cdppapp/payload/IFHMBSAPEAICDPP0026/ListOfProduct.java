package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0026;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "ListOfProduct")
@Getter
@Setter
public class ListOfProduct {
    @Schema(description = "FSC OCN unique code. This is the key field for integration.", example = "1-RGHT2")
    @JsonProperty("Name")
    private String name;
    @Schema(description = "Name of external system.", example = "Source")
    @JsonProperty("Source")
    private String source;
    @Schema(description = "Connex product type.", example = "Transportation")
    @JsonProperty("Type")
    private String type;
    @Schema(description = "If informed, adds a new entry and expires existing entries in the price list HMB Suggested Public Price., HMB Vehicle Suggested Price", example = "50000")
    private String suggestedPrice;
}