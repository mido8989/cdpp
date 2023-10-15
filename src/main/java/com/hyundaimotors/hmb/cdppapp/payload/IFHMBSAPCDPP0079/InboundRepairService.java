package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPCDPP0079;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InboundRepairService {
    @Schema(description = "soid", example = "0002345")
    private String soid;
    @Schema(description = "dealercode", example = "B05AM12345")
    private String dealercode;
    @Schema(description = "vincode", example = "9BH12345678901234")
    private String vincode;
    @Schema(description = "itmnum", example = "001")
    private String itmnum;
    @Schema(description = "sotype", example = "CSP-ISP")
    private String sotype;
    @Schema(description = "codsrc", example = "A02E5")
    private String codsrc;
    @Schema(description = "zdesc", example = "TEST")
    private String zdesc;
    @Schema(description = "zqty", example = "3")
    private float zqty;
    @Schema(description = "unit", example = "H")
    private String unit;
    @Schema(description = "value", example = "120")
    private float value;
    @Schema(description = "totalValue", example = "360")
    private float totalValue;
    @Schema(description = "ernam", example = "SAPUSR01")
    private String ernam;
    @Schema(description = "erdat", example = "27092023")
    private String erdat;
    @Schema(description = "erzet", example = "182636")
    private String erzet;
    @Schema(description = "aenam", example = "SAPUSR01")
    private String aenam;
    @Schema(description = "aedat", example = "27092023")
    private String aedat;
    @Schema(description = "aezet", example = "183832")
    private String aezet;
}
