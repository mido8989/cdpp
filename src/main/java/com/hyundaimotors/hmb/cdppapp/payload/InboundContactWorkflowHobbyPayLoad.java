package com.hyundaimotors.hmb.cdppapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Schema(name = "hobby")
@Getter
@Setter
public class InboundContactWorkflowHobbyPayLoad {
    @NotNull
    @Schema(description = "Hobby name.", example = "Dance")
    public String description;
}
