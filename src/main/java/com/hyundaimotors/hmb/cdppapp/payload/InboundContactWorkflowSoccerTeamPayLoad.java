package com.hyundaimotors.hmb.cdppapp.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class InboundContactWorkflowSoccerTeamPayLoad {
    @Schema(description = "Soccer team name. Valid values:Ponte Preta", example = "Ponte Preta")
    public String Name; 
}
