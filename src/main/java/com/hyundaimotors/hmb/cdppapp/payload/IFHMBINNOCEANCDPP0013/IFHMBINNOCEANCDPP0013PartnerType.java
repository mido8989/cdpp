package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "IFHMBINNOCEANCDPP0013PartnerType")
@Getter
@Setter
public class IFHMBINNOCEANCDPP0013PartnerType {
    @Schema(description = "Partner Type", example = "Dealer")
    private String partnerType;
}
