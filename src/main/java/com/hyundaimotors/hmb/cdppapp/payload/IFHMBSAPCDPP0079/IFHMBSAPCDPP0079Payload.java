package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPCDPP0079;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBSAPCDPP0079Payload {
    
    @Schema(name = "IFHMBSAPCDPP0079Payload.Request")
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "soid", example = "0002345")
        @JsonProperty("CODIGO_OS")
        private String soid;
        @Schema(description = "dealercode", example = "B05AM12345")
        @JsonProperty("DEALER_CODE")
        private String dealercode;
        @Schema(description = "vincode", example = "9BH12345678901234")
        @JsonProperty("CHASSI")
        private String vincode;
        @Schema(description = "mileage", example = "100.02")
        @JsonProperty("KM_VEICULO")
        private float mileage;
        @Schema(description = "plate", example = "AAA-1234")
        @JsonProperty("PLACA_VEICULO")
        private String plate;
        @Schema(description = "status", example = "99")
        @JsonProperty("STATUS_OS")
        private int status;
        @Schema(description = "quicksrv", example = "X")
        @JsonProperty("QUICK_SERVICE")
        private String quicksrv;
        @Schema(description = "consultCpf", example = "11122233345")
        @JsonProperty("CPF_CONSULTOR")
        private String consultCpf;
        @Schema(description = "consultName", example = "JOSÉ SILVA DE SOUZA")
        @JsonProperty("CONSULTOR_SERVICOS")
        private String consultName;
        @Schema(description = "opendt", example = "20230927170725")
        @JsonProperty("DATA_ABERTURA_OS")
        private String opendt;
        @Schema(description = "schedulingdt", example = "20230927170725")
        @JsonProperty("DATA_AGENDAMENTO")
        private String schedulingdt;
        @Schema(description = "comments", example = "Comentário teste")
        @JsonProperty("SOLICITACAO_CLIENTE")
        private String comments;
        @Schema(description = "filesentdt", example = "20230927170725")
        @JsonProperty("DATA_ENVIO_ARQUIVO")
        private String filesentdt;
        @Schema(description = "totalWorked_hours", example = "5.8")
        @JsonProperty("QUANTIDADE_HORAS_TRABALHADAS")
        private float totalWorkedHours;
        @Schema(description = "totalSold_hours", example = "6.2")
        @JsonProperty("QUANTIDADE_HORAS_VENDIDAS")
        private float totalSoldHours;
        @Schema(description = "totalValue", example = "4280.85")
        @JsonProperty("VALOR_TOTAL_OS")
        private float totalValue;
        @Schema(description = "firstname", example = "MARIA DAS GRAÇAS")
        @JsonProperty("NOME_CLIENTE")
        private String firstname;
        @Schema(description = "lastname", example = "DA SILVA")
        @JsonProperty("NOME2_CLIENTE")
        private String lastname;
        @Schema(description = "cpf", example = "98765432101")
        @JsonProperty("CPF_CLIENTE")
        private String cpf;
        @Schema(description = "city", example = "SÃO PAULO")
        @JsonProperty("CIDADE_CLIENTE")
        private String city;
        @Schema(description = "postlCod1", example = "01234-567")
        @JsonProperty("CEP_CLIENTE")
        private String postlCod1;
        @Schema(description = "street", example = "RUA TESTES")
        @JsonProperty("RUA_CLIENTE")
        private String street;
        @Schema(description = "house_no", example = "123")
        @JsonProperty("NUM_CASA_CLIENTE")
        private String houseNo;
        @Schema(description = "country", example = "BRA")
        @JsonProperty("PAIS_CLIENTE")
        private String country;
        @Schema(description = "region", example = "SP")
        @JsonProperty("UF_CLIENTE")
        private String region;
        @Schema(description = "email", example = "testes@teste.com")
        @JsonProperty("EMAIL_CLIENTE")
        private String email;
        @Schema(description = "telNumber", example = "1123456789")
        @JsonProperty("FONE_CLIENTE")
        private String telNumber;
        @Schema(description = "mobNumber", example = "11987654321")
        @JsonProperty("CELULAR_CLIENTE")
        private String mobNumber;
        @Schema(description = "codigoCliente", example = " ")
        @JsonProperty("CODIGO_CLIENTE")
        private String codigoCliente;
        @JsonProperty("ListOfService")
        List<InboundRepairService> listOfService;
        @JsonProperty("ListOfParts")
        List<InboundRepairParts> listOfParts;
    }

    @Schema(name = "IFHMBSAPCDPP0079Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "errorSpcCode", example = "0")
        @JsonProperty("Error_spcCode")
        private String errorSpcCode;
        @Schema(description = "errorSpcMessage", example = "OK")
        @JsonProperty("Error_spcMessage")
        private String errorSpcMessage;
    }
}
