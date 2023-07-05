package com.hyundaimotors.hmb.cdppapp.dto;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;


@Data
public class TestDto implements Serializable{
    
    private String id;
    private String albumName;
    private String releaseDate;
    private String specialChar;
    private Long whatNum;
    
    

}
