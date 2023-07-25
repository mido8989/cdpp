package com.hyundaimotors.hmb.cdppapp.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.AccountDto;
import com.hyundaimotors.hmb.cdppapp.dto.s_contactDto;
import com.hyundaimotors.hmb.cdppapp.dto.TestDto;
import com.hyundaimotors.hmb.cdppapp.payload.AccountPayLoad;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowPayLoad;
import com.hyundaimotors.hmb.cdppapp.service.TestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "테스트", description = "테스트페이지 입니다.")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private final TestService testService;

    private final ModelMapper defaultMapper;

    private static final Logger log = LoggerFactory.getLogger(JdbcTemplate.class);

    @PostMapping(value = "/insert")
    public TestDto insertList(@RequestBody TestDto dto) throws Exception {

        TestDto responDto = testService.insertList(dto);

        return responDto;
    }


    @GetMapping(value = "/list")
    public TestDto getList(@RequestBody TestDto dto) throws Exception {
        TestDto responDto = testService.getList(dto);
        return responDto;
    }
    
    
    @GetMapping("/get/{id}")
    public TestDto getRecord(@PathVariable("id") String recordId) throws Exception {
        TestDto param = new TestDto();
        param.setId(recordId);
        TestDto responDto = testService.getList(param);
        
        return responDto;
    }
    @Operation(summary = "Account 저장", description = "Account 저장 메서드입니다.")
    @ApiResponse(content = @Content(schema = @Schema(implementation = AccountPayLoad.Response.class)))
    @PostMapping(value = "/api/v1/Account")
    public Object accountList(@RequestBody AccountPayLoad.Request request)throws Exception{
        

        AccountDto dto = defaultMapper.map(request, AccountDto.class);

        AccountDto resultDto = testService.getAccountList(dto);

        return defaultMapper.map(resultDto, AccountPayLoad.Response.class);
    }
}
