package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPCDPP0079;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InboundRepair {
    @Schema(description = "soid", example = "0002345")
    private String soid;
    @Schema(description = "dealercode", example = "B05AM12345")
    private String dealercode;
    @Schema(description = "vincode", example = "9BH12345678901234")
    private String vincode;
    @Schema(description = "mileage", example = "100.02")
    private float mileage;
    @Schema(description = "model", example = "CRETA ACTION")
    private String model;
    @Schema(description = "zyear", example = "2023")
    private int zyear;
    @Schema(description = "plate", example = "AAA-1234")
    private String plate;
    @Schema(description = "status", example = "99")
    private int status;
    @Schema(description = "quicksrv", example = "X")
    private String quicksrv;
    @Schema(description = "consultCpf", example = "11122233345")
    private String consultCpf;
    @Schema(description = "consultName", example = "JOSÉ SILVA DE SOUZA")
    private String consultName;
    @Schema(description = "opendt", example = "20230927170725")
    private String opendt;
    @Schema(description = "closedt", example = "20230927170725")
    private String closedt;
    @Schema(description = "canceldt", example = "20230927170725")
    private String canceldt;
    @Schema(description = "deliverydt", example = "20230927170725")
    private String deliverydt;
    @Schema(description = "schedulingdt", example = "20230927170725")
    private String schedulingdt;
    @Schema(description = "comments", example = "Comentário teste")
    private String comments;
    @Schema(description = "filesentdt", example = "20230927170725")
    private String filesentdt;
    @Schema(description = "totalWorked_hours", example = "5.8")
    @JsonProperty("totalWorked_Hours")
    private float totalWorkedHours;
    @Schema(description = "totalSold_hours", example = "6.2")
    @JsonProperty("totalSold_Hours")
    private float totalSoldHours;
    @Schema(description = "totalValue", example = "4280.85")
    private float totalValue;
    @Schema(description = "partner", example = "000987")
    private String partner;
    @Schema(description = "firstname", example = "MARIA DAS GRAÇAS")
    private String firstname;
    @Schema(description = "lastname", example = "DA SILVA")
    private String lastname;
    @Schema(description = "cpf", example = "98765432101")
    private String cpf;
    @Schema(description = "city", example = "SÃO PAULO")
    private String city;
    @Schema(description = "postlCod1", example = "01234-567")
    private String postlCod1;
    @Schema(description = "street", example = "RUA TESTES")
    private String street;
    @Schema(description = "house_no", example = "123")
    @JsonProperty("house_No")
    private String houseNo;
    @Schema(description = "country", example = "BRA")
    private String country;
    @Schema(description = "region", example = "SP")
    private String region;
    @Schema(description = "taxjurcode", example = "abc")
    private String taxjurcode;
    @Schema(description = "email", example = "testes@teste.com")
    private String email;
    @Schema(description = "telNumber", example = "1123456789")
    private String telNumber;
    @Schema(description = "mobNumber", example = "11987654321")
    private String mobNumber;
    @Schema(description = "blockcontact", example = "X")
    private String blockcontact;
    @Schema(description = "queried", example = "20230927")
    private String queried;
    @Schema(description = "ernam", example = "SAPUSR01")
    private String ernam;
    @Schema(description = "erdat", example = "20230927")
    private String erdat;
    @Schema(description = "erzet", example = "171838")
    private String erzet;
    @Schema(description = "aenam", example = "SAPUSR01")
    private String aenam;
    @Schema(description = "aedat", example = "20230927")
    private String aedat;
    @Schema(description = "aezet", example = "171850")
    private String aezet;
    @JsonProperty("ListOfService")
    List<InboundRepairService> listOfService;
    @JsonProperty("ListOfParts")
    List<InboundRepairParts> listOfParts;
}
