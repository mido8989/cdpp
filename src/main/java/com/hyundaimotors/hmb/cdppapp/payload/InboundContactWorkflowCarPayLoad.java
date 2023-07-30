package com.hyundaimotors.hmb.cdppapp.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class InboundContactWorkflowCarPayLoad {
    @Schema(description = "Car name.", example = "HB20")
    public String Name;
    @Schema(description = "Car model year..", example = "2015")
    public String YearModel;
    @Schema(description = "Intention to change car. Valid values:Coming soon, From 1 to 3 months, I do not know, 1 Month .", example = "Comimg soon")
    public String IntentionToChange;
    @Schema(description = "Flag to define if the car is customer's current car..", example = "Y")
    public String isCurrent;
    @Schema(description = "Car model.", example = "2015")
    public String CarModel;
    @Schema(description = "Car mileage.", example = "1000")
    public String mileage;
    @Schema(description = "Vehicle Client Expected Value information.", example = "Uniinformed")
    public String ClientExpectedValue;
}
