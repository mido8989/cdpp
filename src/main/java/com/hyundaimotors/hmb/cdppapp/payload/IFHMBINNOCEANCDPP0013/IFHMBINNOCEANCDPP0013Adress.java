package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "IFHMBINNOCEANCDPP0013Adress")
@Getter
@Setter
public class IFHMBINNOCEANCDPP0013Adress {
    private String addressRowId;
    @Schema(description = "AddressName, Integration Id", example = "B05AM99999")
    private String addressName;
    @Schema(description = "Address street name.", example = "Av Sao 001")
    private String streetAddress;
    @Schema(description = "Address house number", example = "1083")
    private String streetAddress2;
    @Schema(description = "Address complement.", example = "Andar 3")
    private String streetAddress3;
    @Schema(description = "Address county.", example = "Alem Ponte")
    private String county;
    @Schema(description = "Address city.", example = "Sorocaba")
    private String city;
    @Schema(description = "Address state.", example = "SP")
    private String state;
    @Schema(description = "Address Latitude", example = "23.497694")
    private int latitude;
    @Schema(description = "Address Longitude", example = "47.441679")
    private int longitude;
    @Schema(description = "Address zip code.", example = "18085450")
    private String zipCode;
}