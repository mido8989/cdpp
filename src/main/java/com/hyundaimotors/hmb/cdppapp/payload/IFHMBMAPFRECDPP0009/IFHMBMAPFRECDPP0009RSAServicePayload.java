package com.hyundaimotors.hmb.cdppapp.payload.IFHMBMAPFRECDPP0009;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "IFHMBMAPFRECDPP0009RSAServicePayload")
@Getter
@Setter
public class IFHMBMAPFRECDPP0009RSAServicePayload {
    
    @Schema(description = "Integration Id", example = "Conveni 00EAncia-Rede el 00E1stica para porta-malas")
    private String serviceId;
    @Schema(description = "Service Date", example = "2015-08-09")
    private String createDate;
    @Schema(description = "Service Code", example = "R40 - CONSERTO MEC 00C2NICO NO LOCAL")
    private String serviceCode;
    @Schema(description = "Specialty Code", example = "R40M - PADR00C3O")
    private String specialty;
    @Schema(description = "Service Provider Name", example = "R$229.2600")
    private String provider;
    @Schema(description = "Vehicle Issue Code", example = "Apagou. C00F3d 0")
    private String vehicleProblem;
    @Schema(description = "Problem Description", example = "VCL N00C3O LIGA")
    private String problemDescription;
    @Schema(description = "Expected Arrival", example = "1230000")
    private int tmcPredicted;
    @Schema(description = "Important Description", example = "VCL EM GARANTIA // SOMENTE RECARGA DE BATERIA // VCL NO 2\u00BA SUBSOLO // AGILIDADE CLT ATRASADO")
    private String important;
    @Schema(description = "Request Status", example = "1")
    private String requestStatus;
    @Schema(description = "Actuation Status", example = "Enviando limusine")
    private String actuationStatus;
    @Schema(description = "Address street name.", example = "Rua Tutoia")
    private String addressName;
    @Schema(description = "Address house number", example = "380")
    private String addressNumber;
    @Schema(description = "Address complement.", example = "Andar 3")
    private String addressComplement;
    @Schema(description = "Address county.", example = "Paraiso")
    private String county;
    @Schema(description = "Address city.", example = "Brasil")
    private String city;
    @Schema(description = "Address state.", example = "S00E3o Paulo ")
    private String state;
    @Schema(description = "Address latitude", example = "1230000")
    private int latitude;
    @Schema(description = "Address longitude", example = "1230000")
    private int longitude;
    @Schema(description = "Address reference", example = "G2 // PROX AO MERCADO PRINCESA // PROX A RENAULT")
    private String reference;
    @Schema(description = "Address zip code.", example = "4007001")
    private String zipCode;
    @Schema(description = "Dealer Name", example = "CAOA S/A")
    private String dealerName;
    @Schema(description = "Dealer Nickname", example = "Rua P00E2ntano da Ignom00EDnia")
    private String dealerNickname;
    @Schema(description = "Expense", example = "R$229.2600")
    private String expense;
}
