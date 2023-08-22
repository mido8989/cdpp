package com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelPartnerBusinessAdress {
    private String rowId;
    private String addressName;
    private String streetAddress;
    private String streetAddress2;
    private String streetAddress3;
    private String county;
    private String city;
    private String state;
    private int latitude;
    private int longitude;
    private String zipCode;
}
