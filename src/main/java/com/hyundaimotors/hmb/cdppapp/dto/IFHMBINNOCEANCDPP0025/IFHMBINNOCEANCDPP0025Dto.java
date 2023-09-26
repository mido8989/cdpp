package com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0025;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0025.ScheduleMaintenanceIN;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0025Dto {
    
    ScheduleMaintenanceINDto ScheduleMaintenanceIN;
    private String dtoSrnumber;
    private String ErrorSpcCode;
    private String ErrorSpcMessage;
}
