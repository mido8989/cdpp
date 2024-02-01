package com.hyundaimotors.hmb.cdppapp.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AccountRequestCaches {

    private static AccountRequestCaches instance;
    private static ObjectMapper mapper;
    private static long cacheMinutes = 1;

    private java.util.Map<JsonNode, LocalDateTime> cacheMap = null;

    private AccountRequestCaches() {
        if (this.cacheMap == null) {
            this.cacheMap = new HashMap<>();
        }
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
    }

    public static AccountRequestCaches getInstance() {
        if (instance == null) {
            instance = new AccountRequestCaches();
        }
        return instance;
    }

    public LocalDateTime getMapObject(String keyString) throws JsonMappingException, JsonProcessingException {
        return getMapObject(mapper.readTree(keyString));
    }

    public LocalDateTime getMapObject(JsonNode key) {
        return getMapObjectInTime(key);
    }

    public LocalDateTime getMapObjectInTime(JsonNode key) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime cachedAt = this.cacheMap.get(key);

        if (cachedAt == null || cachedAt.plusMinutes(cacheMinutes).isBefore(now)) {
            //캐시되었으나 캐시만료 시간이 지난 경우
            setMapObject(key);
            return null;
        } else {
            return cachedAt;
        }
    }

    public void setMapObject(String key) throws JsonMappingException, JsonProcessingException {
        setMapObject(mapper.readTree(key));
    }

    public void setMapObject(JsonNode key) {
        this.cacheMap.put(key, LocalDateTime.now());
    }

    public void setMapObject(String key, LocalDateTime value) throws JsonMappingException, JsonProcessingException {
        setMapObject(mapper.readTree(key), value);
    }

    public void setMapObject(JsonNode key, LocalDateTime value) {
        if (value == null) {
            setMapObject(key);
        } else {
            this.cacheMap.put(key, value);
        }
    }

    public void clearExpiredCache() {
        java.util.List<JsonNode> removeTargetList = new ArrayList<>();
        for (JsonNode key : this.cacheMap.keySet()) {
            // System.out.println("Clear Search Key ==> " + key);
            LocalDateTime val = this.cacheMap.get(key);
            if (val != null && val.plusMinutes(cacheMinutes).isBefore(LocalDateTime.now())) {
                // System.out.println("Clear Target Val ==> " + val.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
                removeTargetList.add(key);
            }
        }
        if (!removeTargetList.isEmpty()) {
            for (JsonNode key : removeTargetList) {
                this.cacheMap.remove(key);
            }
        }
    }
}
