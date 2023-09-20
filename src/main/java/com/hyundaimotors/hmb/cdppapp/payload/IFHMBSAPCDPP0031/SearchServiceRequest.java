package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPCDPP0031;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "SearchServiceRequest")
@Getter
@Setter
public class SearchServiceRequest {
    @Schema(description = "RSA Integration Id", example = "MONDIAL29129941")
    private String rsaIntegrationId;
    @Schema(description = "Id", example = "Id")
    private String id;
    @Schema(description = "TestDriveDate", example = "1-1238L")
    private String testDriveDate;
    @Schema(description = "Test DriveD ate", example = "14:00~16:00")
    private String testDriveTime;
    @Schema(description = "Test Drive Time", example = "2015-09-12")
    private String testDriveConfirmed;
    @Schema(description = "Created", example = "2015-09-10")
    private String created;
    @Schema(description = "Created By", example = "1-1GQA")
    private String createdBy;
    @Schema(description = "Updated", example = "2015-09-14")
    private String updated;
    @Schema(description = "Updated By", example = "1-1GQA")
    private String updatedBy;
    @Schema(description = "Sales Rep FirstName", example = "Jose")
    private String salesRepFirstName;
    @Schema(description = "Sales Rep LastName", example = "Ciclano")
    private String salesRepLastName;
    @Schema(description = "Dealer", example = "B05AM09004")
    private String dealer;
    @Schema(description = "Conflict Id", example = "N")
    private String conflictId;
    @Schema(description = "Mod Id", example = " ")
    private String modId;
    @Schema(description = "Codigo Sap TD", example = "345678")
    private String codigoSAPTD;
    @Schema(description = "Número do protocolo", example = "HMB98949022323")
    private String protocol;
    @Schema(description = "Seach  Specification", example = "Y")
    private String searchspec;
    @Schema(description = "Status", example = "Canceled")
    private String status;
    @Schema(description = "Model", example = "HB20")
    private String model;
    @Schema(description = "ContactId", example = "1-3U58H1")
    private String contactId;
    @Schema(description = "Area", example = "Test drive")
    private String area;
    @Schema(description = "CPF", example = "12337343006")
    private String cpf;
    @Schema(description = "Origem", example = "Customer")
    private String origem;
    @Schema(description = "SRId", example = "1-1GQA")
    private String srId;
    @Schema(description = "Selected SR Routing Dealer Code", example = "B05AM09004")
    private String selectedSRRoutingDealerCode;
}
