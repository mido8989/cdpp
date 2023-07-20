package com.hyundaimotors.hmb.cdppapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class IFHMBZICARDCDPP0001Payload {
    
    @Schema(name = "IFHMBZICARDCDPP0001Payload.Request")
    @Getter
    @Setter
    public static class Request{
        
        @NotNull
        @Schema(description = "phone Number", example = "+5511976588820")
        private String phoneNumber;
        
        @Schema(description = "cpfNumber", example = "30311079800")
        private String cpf;
        
    }

    @Schema(name = "IFHMBZICARDCDPP0001Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "cpfNumber", example = "12337343006")
        private String cpf;
        @Schema(description = "ContactId", example = "1-90ABC")
        private String contactId;
        @Schema(description = "Full name", example = "Jo\u00E3o de Jesus Atan\u00E1zio")
        private String name;
        @Schema(description = "personCell", example = "5511976588820")
        private String personCell;
        @Schema(description = "protocolNumber", example = "HMB98949022323")
        private String protocolNumber;
        @Schema(description = "protocolOwnerLogin", example = "HMBJBSOUZA")
        private String protocolOwnerLogin;
        @Schema(description = "protocolOwnerName", example = "Joaquim de Sousa")
        private String protocolOwnerName;
        @Schema(description = "protocolStatus", example = "In Progress BO")
        private String protocolStatus;
        @Schema(description = "errorSpcCode", example = "SBL-12Z3Z")
        private String errorSpcCode;
        @Schema(description = "errorSpcMessage", example = "Erro ao executar a tarefa  X do Connex.")
        private String errorSpcMessage;
    }
}