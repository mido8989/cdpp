package com.hyundaimotors.hmb.cdppapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AutoVehicleWebserviceWFContactPayload {
    @Schema(description = "Connex contact id., HKME Asset Contact Id", example = "1-U1V4")
    @NotNull
    public String connexContactId;
    @Schema(description = "Relationship type between asset and contact. Valid values: Assignee, Driver, Family, First owner, Mechanic, Other, Owner, Unspecified.", example = "Driver")
    public String type;
    @Schema(description = "Status of the relationship between asset and contact. Valid values: Active, Inactive.", example = "Ianctive")
    public String status;
    @Schema(description = "Is Buyer Employee Flag? Valid values: Y, N.", example = "Y")
    public String employee;
    public String assetRowId; // Dto역할 할 때만 임시로 쓰이는 필드
    
}
