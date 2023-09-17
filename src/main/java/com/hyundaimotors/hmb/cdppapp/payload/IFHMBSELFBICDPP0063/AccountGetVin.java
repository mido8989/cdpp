package com.hyundaimotors.hmb.cdppapp.payload.IFHMBSELFBICDPP0063;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Account")
@Getter
@Setter
public class AccountGetVin {
    private String IdSiebel;
    private String RelatedType;
    private String RelatedStatus;
}
