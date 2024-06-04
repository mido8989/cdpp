package com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPCDPP0005;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountWebserviceAuditDto {
    private String oldValue;
    private String newValue;
    private String fieldName;
    private String operation;
    private String rowId;
    private String apiKey;
}
