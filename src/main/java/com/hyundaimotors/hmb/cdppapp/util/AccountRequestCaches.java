package com.hyundaimotors.hmb.cdppapp.util;

import java.util.HashMap;

public class AccountRequestCaches {

    private static AccountRequestCaches instance;

    private java.util.Map<String, Object> cacheMap = null;

    private AccountRequestCaches() {
        if (this.cacheMap == null) {
            this.cacheMap = new HashMap<>();
        }
    }

    public static AccountRequestCaches getInstance() {
        if (instance == null) {
            instance = new AccountRequestCaches();
        }
        return instance;
    }

    public Object getMapObject(String key) {
        return this.cacheMap.get(key);
    }

    public void setMapObject(String key, Object value) {
        this.cacheMap.put(key, value);
    }
}
