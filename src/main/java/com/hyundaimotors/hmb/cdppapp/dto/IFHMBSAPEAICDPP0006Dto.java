package com.hyundaimotors.hmb.cdppapp.dto;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0006.Action;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0006.ActionNote;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0006.ActionProducts;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBSAPEAICDPP0006.SynergyActionAudit;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBSAPEAICDPP0006Dto {
    private int rowId;
    Action action;
    List<ActionNote> actionNote;
    List<ActionProducts> actionProducts;
    List<SynergyActionAudit> synergyActionAudit;
    private String actionId;
    private String errorSpcCode;
    private String errorSpcMessage;
}
