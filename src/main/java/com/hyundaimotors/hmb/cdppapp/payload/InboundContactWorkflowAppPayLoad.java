package com.hyundaimotors.hmb.cdppapp.payload;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Schema(name = "app")
@Getter
@Setter
public class InboundContactWorkflowAppPayLoad {
    @NotNull
    @Schema(description = "App name download by customer. Valid values:HB20", example = "HB20")
    public String name;
    @NotNull
    @Schema(description = "Download date. Format: MM/DD/YYYY", example = "1980-10-21")
    public String startDate;
    @NotNull
    @Schema(description = "Flag to inform if app is still active or was removed by customer. Valid values: Y, N", example = "Y")
    public String status;
}
