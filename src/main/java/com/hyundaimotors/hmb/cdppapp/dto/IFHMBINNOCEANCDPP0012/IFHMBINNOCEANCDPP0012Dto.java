package com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0012;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0012.ChannelPartner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0012Dto {
        private String accountRowId;
        private String source;
        private String errorSpcCode;
        private String errorSpcMessage;

        private List<ChannelPartnerDto> channelPartner;
}
