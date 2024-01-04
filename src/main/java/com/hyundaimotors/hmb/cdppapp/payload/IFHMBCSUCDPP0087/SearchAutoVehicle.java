package com.hyundaimotors.hmb.cdppapp.payload.IFHMBCSUCDPP0087;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "SearchAutoVehicle")
@Getter
@Setter
public class SearchAutoVehicle {
    @Schema(description = "Asset Id", example = "1-1DL-53")
    @JsonProperty("VehicleId")
    private String vehicleId;
    @Schema(description = "Relationship", example = "Owner")
    @JsonProperty("Relationship")
    private String relationship;
    @Schema(description = "Relationship", example = "Owner")
    @JsonProperty("RelashionshipStatus")
    private String relashionshipStatus;
    @Schema(description = "Renavam Code", example = "497269414")
    @JsonProperty("HMBRenavamCode")
    private String hmbRenavamCode;
    @Schema(description = "Curren Mileage", example = "24350")
    @JsonProperty("CurrenMileage")
    private String currenMileage;
    @Schema(description = "Vehicle License Number", example = "FGZ 4544")
    @JsonProperty("VehicleLicenseNumber")
    private String vehicleLicenseNumber;
    @Schema(description = "Chassis number.", example = "9BHBH41DBGP633658")
    @JsonProperty("SerialNumber")
    private String serialNumber;
    @Schema(description = "Product FSC OCN Name", example = "092214A000")
    @JsonProperty("FSCOCN")
    private String fscOcn;
    @Schema(description = "Manufacture year.", example = "2012")
    @JsonProperty("ManufactureYear")
    private String manufactureYear;
    @Schema(description = "Model", example = "2015")
    @JsonProperty("Model")
    private String model;
    @Schema(description = "Model Year", example = "2013")
    @JsonProperty("ModelYear")
    private String modelYear;
    @Schema(description = "Internal color code.", example = "9P")
    @JsonProperty("InteriorColor")
    private String internalColorCode;
    @Schema(description = "External color code.", example = "R5S")
    @JsonProperty("ExteriorColor")
    private String externalColorCode;
    @Schema(description = "Hexa Warranty's Flag", example = "HB20")
    @JsonProperty("HexaWarrantyFlag")
    private String hexaWarrantyFlag;
    @Schema(description = "Connex dealer id.", example = "1-7890WX")
    @JsonProperty("DealerId")
    private String dealerId;
    @Schema(description = "Retail Date", example = "B05AM19029")
    @JsonProperty("RetailDt")
    private String retailDt;
    @Schema(description = "Dealer nickname.", example = "Via Imperial - Petrópolis")
    @JsonProperty("SalesRepId")
    private String salesRepId;
    @Schema(description = "Sales Rep LastName", example = "Renato")
    @JsonProperty("SalesRepLastName")
    private String salesRepLastName;
    @Schema(description = "SalesRep FirstName", example = "Fernandez")
    @JsonProperty("SalesRe FirstName")
    private String salesReFirstName;
    @Schema(description = "Operaying Status", example = "Completed")
    @JsonProperty("OperatingStatus")
    private String operatingStatus;
}
