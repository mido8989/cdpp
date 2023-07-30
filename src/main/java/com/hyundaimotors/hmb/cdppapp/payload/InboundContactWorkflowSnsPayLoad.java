package com.hyundaimotors.hmb.cdppapp.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class InboundContactWorkflowSnsPayLoad {
    @Schema(description = "Social media name. Valid values: Facebook", example = "Facebook")
    public String Name;
    @Schema(description = "User name in the social media. (not login name, but the display name)", example = "Gianca ")
    public String User;
    @Schema(description = "Social media join date.", example = "1980-10-21")
    public String JoinDate;
    @Schema(description = "Indicates if customer liked HMB page on facebook. Valid values:true, False", example = "False")
    public String LikeHMB;
}
