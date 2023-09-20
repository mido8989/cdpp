package com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPCDPP0031;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBSAPCDPP0031Dto {
    private String dateInput;
    private String createdOption;
    private String errorSpcCode;
    private String errorSpcMessage;
    private List<SearchServiceRequestDto> listOfHmbSearchServiceRequest;
}
