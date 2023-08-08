package com.hyundaimotors.hmb.cdppapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AutoVehicleWebserviceWFAccountPayload {
    @Schema(description = "Connex account id.", example = "1-4VKWG9")
    @NotNull
    public String connexAccountId;
    @Schema(description = "Relationship type between asset and account. Valid values: Assignee, Driver, Family, First owner, Mechanic, Other, Owner, Unspecified.", example = "Owner")
    public String type;
    @Schema(description = "Status of the relationship between asset and account. Valid values: Active, Inactive.", example = "Active")
    public String status;
    
}
