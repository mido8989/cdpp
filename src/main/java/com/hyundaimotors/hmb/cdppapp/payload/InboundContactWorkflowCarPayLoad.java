package com.hyundaimotors.hmb.cdppapp.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class InboundContactWorkflowCarPayLoad {
    public String parRowId;
    @Schema(description = "Car name.", example = "HB20")
    public String name;
    @Schema(description = "Car model year..", example = "2015")
    public String yearModel;
    @Schema(description = "Intention to change car. Valid values:Coming soon, From 1 to 3 months, I do not know, 1 Month .", example = "Comimg soon")
    public String intentionToChange;
    @Schema(description = "Flag to define if the car is customer's current car..", example = "Y")
    public String isCurrent;
    @Schema(description = "Car model.", example = "2015")
    public String carModel;
    @Schema(description = "Car mileage.", example = "1000")
    public int mileage;
    @Schema(description = "Vehicle Client Expected Value information.", example = "5")
    public int clientExpectedValue;
}
