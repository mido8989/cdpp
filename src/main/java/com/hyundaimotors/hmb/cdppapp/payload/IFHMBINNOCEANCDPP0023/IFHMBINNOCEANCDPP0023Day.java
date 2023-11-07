package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0023;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "IFHMBINNOCEANCDPP0023Day")
@Getter
@Setter
public class IFHMBINNOCEANCDPP0023Day {
    @Schema(description = "Day name. Possible values are: Sunday ~ Saturday ", example = "Friday")
    @NotNull @NotBlank @NotEmpty
    @JsonProperty("day-name")
    @NotNull @NotBlank @NotEmpty
    private String dayName;
    @Schema(description = "First working period start time. Format: HH:MM:SS", example = "2017-11-15T08:00:00-0300")
    @NotNull @NotBlank @NotEmpty
    private String period1Start;
    @Schema(description = "Second working period end time. Format: HH:MM:SS", example = "2017-11-15T12:00:00-0300")
    @NotNull @NotBlank @NotEmpty
    private String period1End;
    @Schema(description = "Second working period start time. Format: HH:MM:SS", example = "2017-11-15T14:00:00-0300")
    private String period2Start;
    @Schema(description = "Second working period end time. Format: HH:MM:SS", example = "2017-11-15T18:00:00-0300")
    private String period2End;
    @Schema(description = "Third working period start time. Format: HH:MM:SS", example = "2017-11-15T20:00:00-0300")
    private String period3Start;
    @Schema(description = "Third working period end time. Format: HH:MM:SS", example = "2017-11-15T22:00:00-0300")
    private String period3End;
}
