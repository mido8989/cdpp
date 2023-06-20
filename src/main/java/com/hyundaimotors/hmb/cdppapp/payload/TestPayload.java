package com.hyundaimotors.hmb.cdppapp.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TestPayload {

    @Data
    public static class Response{
        
        private String albumName;
        private String specialChar;
        private Long whatNum;
        private String releaseDate;
    }
}
