package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSMARTERSCDPP0036;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class IFHMBSMARTERSCDPP0036Payload {
    
    @Schema(name = "IFHMBSMARTERSCDPP0036Payload.Request")
    @Getter
    @Setter
    public static class Request{
        ServiceRequestChatInput serviceRequestChatInput;
        List<Chat> listOfChat;
        List<Message> listOfMessage;
    }

    @Schema(name = "IFHMBSMARTERSCDPP0036Payload.Response")
    @Getter
    @Setter
    public static class Response{
        @Schema(description = "errorSpcCode", example = "SBL-12Z3Z")
        private String errorSpcCode;
        @Schema(description = "errorSpcMessage", example = "Erro ao executar a tarefa  X do Connex.")
        private String errorSpcMessage;
        @Schema(description = "Owner By Id", example = "1-3LHJF40")
        private String ownedById;
        @Schema(description = "Protocol unique identifier. If an update is needed, value on this tag must be provided.", example = "HMB98949022323")
        private String protocol;
    }
}
