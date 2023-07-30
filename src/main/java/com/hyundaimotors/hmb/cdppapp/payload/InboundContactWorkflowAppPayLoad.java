package com.hyundaimotors.hmb.cdppapp.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class InboundContactWorkflowAppPayLoad {
    @Schema(description = "App name download by customer. Valid values:HB20", example = "HB20")
    public String Name;
    @Schema(description = "Download date. Format: MM/DD/YYYY", example = "1980-10-21")
    public String StartDate;
    @Schema(description = "Flag to inform if app is still active or was removed by customer. Valid values: Y, N", example = "Y")
    public String Status;
}
