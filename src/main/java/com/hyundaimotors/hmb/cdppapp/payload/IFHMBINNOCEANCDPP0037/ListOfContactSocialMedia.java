package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "ListOfContactSocialMedia")
@Getter
@Setter
public class ListOfContactSocialMedia {
    @Schema(description = "Social media name. Valid values: Facebook", example = "Facebook")
    private String socialMediaName;
    @Schema(description = "User name in the social media. (not login name, but the display name)", example = "Gianca")
    private String socialMediaUser;
    @Schema(description = "Source City ", example = "Brasil")
    private String sourceCity;

    private String socialMediaContactId;
}
