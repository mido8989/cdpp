package com.hyundaimotors.hmb.cdppapp.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class InboundContactWorkflowCarPayLoad {
    public String Name;
    public String YearModel;
    public String IntentionToChange;
    public String isCurrent;
    public String CarModel;
    public String mileage;
    public String ClientExpectedValue;
}
