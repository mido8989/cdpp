package com.hyundaimotors.hmb.cdppapp.Repository;

import org.springframework.data.repository.CrudRepository;

import com.hyundaimotors.hmb.cdppapp.dto.TestDto;

public interface UserRepo extends CrudRepository<TestDto, TestDto> {
}
