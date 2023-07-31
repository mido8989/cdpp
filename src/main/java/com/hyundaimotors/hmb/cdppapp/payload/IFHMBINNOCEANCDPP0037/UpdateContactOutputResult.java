package com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "UpdateContactOutputResult")
@Getter
@Setter
public class UpdateContactOutputResult {
    private String contactId;
    private String ErrorSpcCode;
    private String ErrorSpcMessage;

}
