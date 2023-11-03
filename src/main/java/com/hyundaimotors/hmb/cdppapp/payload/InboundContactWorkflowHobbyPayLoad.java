package com.hyundaimotors.hmb.cdppapp.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Schema(name = "hobby")
@Getter
@Setter
public class InboundContactWorkflowHobbyPayLoad {
    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(description = "Hobby name.", example = "Dance")
    @JsonProperty("Description")
    public String description;
}
