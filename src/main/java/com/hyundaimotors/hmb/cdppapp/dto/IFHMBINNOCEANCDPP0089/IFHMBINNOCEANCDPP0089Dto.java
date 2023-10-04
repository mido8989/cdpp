package com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0089;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0089.SatisfactionSurvey;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0089Dto {
    
    private String startDate;
    private String endDate;
    private String ErrorSpcCode;
    private String ErrorSpcMessage;
    List<SatisfactionSurveyDto> listOfSatisfactionSurvey;
}
