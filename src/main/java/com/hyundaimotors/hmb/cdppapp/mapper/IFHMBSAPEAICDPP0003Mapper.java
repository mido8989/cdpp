package com.hyundaimotors.hmb.cdppapp.mapper;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBSAPEAICDPP0003Dto;

public interface IFHMBSAPEAICDPP0003Mapper {
    
    public int insertProd(IFHMBSAPEAICDPP0003Dto dto)throws Exception;

    public int insertProdx(IFHMBSAPEAICDPP0003Dto dto)throws Exception;

    public int updateProd(IFHMBSAPEAICDPP0003Dto dto)throws Exception;

    public String getRowIdProdx(IFHMBSAPEAICDPP0003Dto dto)throws Exception;

    public int updateProdx(IFHMBSAPEAICDPP0003Dto dto)throws Exception;
}
