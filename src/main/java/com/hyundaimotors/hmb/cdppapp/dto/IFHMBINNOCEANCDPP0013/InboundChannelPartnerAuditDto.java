package com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InboundChannelPartnerAuditDto {
    private String oldValue;
    private String newValue;
    private String fieldName;
    private String operation;
    private String rowId;
    private String apiKey;
}
