package com.hyundaimotors.hmb.cdppapp.service;

import org.springframework.scheduling.annotation.Async;

import com.hyundaimotors.hmb.cdppapp.dto.ApiLogDto;
import com.hyundaimotors.hmb.cdppapp.util.ApiLogStep;

public interface ApiLogService {
    static final String[] arrExcludeIfId = {"IF079", "IF006"};
    @Async
    public void writeStepLogging(ApiLogStep step, ApiLogDto dto);
}
