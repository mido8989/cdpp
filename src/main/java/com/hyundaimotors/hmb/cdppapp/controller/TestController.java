package com.hyundaimotors.hmb.cdppapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hyundaimotors.hmb.cdppapp.dto.TestDto;
import com.hyundaimotors.hmb.cdppapp.service.impl.TestServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private final TestServiceImpl testService;

    @PostMapping(value = "/insert")
    public TestDto insertList(@RequestBody TestDto dto) throws Exception {

        TestDto responDto = testService.insertList(dto);

        return responDto;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
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
}
