package com.hyundaimotors.hmb.cdppapp.dto;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSMARTERSCDPP0036.Chat;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSMARTERSCDPP0036.Message;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSMARTERSCDPP0036.ServiceRequestChatInput;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBSMARTERSCDPP0036Dto {
    private int rowId;
    ServiceRequestChatInput serviceRequestChatInput;
    List<Chat> listOfChat;
    List<Message> listOfMessage;

    private String errorSpcCode;
    private String errorSpcMessage;
}
