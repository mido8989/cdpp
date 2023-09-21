package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSMARTERSCDPP0036;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Chat")
@Getter
@Setter
public class Chat {
    @Schema(description = "Chat Code", example = "CH0006")
    @NotNull
    @NotEmpty
    private String chatCode;
    @Schema(description = "Date Chat", example = "2020-01-31  10:31:02 AM")
    private String date;
    @Schema(description = "Source Chat", example = "WhatsApp")
    private String source;
    @Schema(description = "Operator Chat", example = "HMBJVICTOR")
    private String operator;

}
