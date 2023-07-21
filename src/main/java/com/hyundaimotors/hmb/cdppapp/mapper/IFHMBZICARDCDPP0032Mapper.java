package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBZICARDCDPP0032Dto;

public interface IFHMBZICARDCDPP0032Mapper {
    
    public IFHMBZICARDCDPP0032Dto getSearchContact(IFHMBZICARDCDPP0032Dto dto)throws Exception;

    public List<IFHMBZICARDCDPP0032Dto> getlistOfServiceRequest(IFHMBZICARDCDPP0032Dto dto)throws Exception;

}
