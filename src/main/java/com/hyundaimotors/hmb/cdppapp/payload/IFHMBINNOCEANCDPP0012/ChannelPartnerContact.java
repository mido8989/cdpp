package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0012;

import io.swagger.v3.oas.annotations.media.Schema;

public class ChannelPartnerContact {
    @Schema(description = "External system unique identifier.", example = "1-7890WX")
    private String integrationId;
    @Schema(description = "Individual's document number (numbers only)", example = "12337343006")
    private String cpf;
    @Schema(description = "Contact first name", example = "Jose")
    private String firstName;
    @Schema(description = "Contact last name", example = "Ciclano")
    private String lastName;
    @Schema(description = "Contact email address.", example = "kilosolution2s@gmail.com")
    private String emailAddress;
    @Schema(description = "Contact home phone.", example = "2345678")
    private String homePhone;
    @Schema(description = "Contact work phone.", example = "3456789")
    private String workPhone;
    @Schema(description = "Contact cell phone.", example = "11941319501")
    private String cellPhone;
    @Schema(description = "Contact job Title", example = "Uniinformed")
    private String jobTitle;
    @Schema(description = "Dealer Contact Status,  HMBDealerContactStatusonDealer", example = "Active")
    private String status;
    @Schema(description = "Dealer Contact Note,  HMBDealerContactNotesonDealer", example = "30694501808")
    private String notes;
}
