package com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0012;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelPartnerContactDto {
    private String integrationId;
    private String cpf;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String homePhone;
    private String workPhone;
    private String cellPhone;
    private String jobTitle;
    private String status;
    private String notes;
}
