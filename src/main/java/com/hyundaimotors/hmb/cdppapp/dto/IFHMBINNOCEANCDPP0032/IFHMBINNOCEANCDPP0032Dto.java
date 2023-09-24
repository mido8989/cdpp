package com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0032;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBZICARDCDPP0032.SearchContactTD;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBZICARDCDPP0032.ServiceRequestTD;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0032Dto {
    private String cpf;
    private String contactId;

    private String ErrorSpcCode;
    private String ErrorSpcMessage;
    
    private List<SearchContactTDDto> ListOfSearchContactTD;
    private List<ServiceRequestTDDto> ListOfServiceRequest;
}
