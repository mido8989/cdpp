package com.hyundaimotors.hmb.cdppapp.util;

import org.apache.commons.lang3.ObjectUtils;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0002.IFHMBINNOCEANCDPP0002Dto;

public class CloneTest {
    public static void main(String[] args) {
        String json = "{ \"apiKey\": \"c851fcaf-5dad-47bc-b151-10c1984e029b\", \"FirstName\": \"Josiele\", \"LastName\": \"Camargo\", \"CellPhone\": \"+5541991082373\", \"EmailAddress\": \"maujosmig@gmail.com\", \"SuppressAllEmails\": \"N\", \"SuppressAllSMS\": \"N\", \"SuppressAllVideoCall\": \"Y\", \"SuppressAllWhatsapp\": \"N\", \"SuppressAllMobile\": \"N\"}";
        try {
            IFHMBINNOCEANCDPP0002Dto obj = JsonUtils.toObject(json, IFHMBINNOCEANCDPP0002Dto.class);
            IFHMBINNOCEANCDPP0002Dto obj2 = JsonUtils.toObject(json, IFHMBINNOCEANCDPP0002Dto.class);
            IFHMBINNOCEANCDPP0002Dto obj3 = ObjectUtils.cloneIfPossible(obj);
            System.out.println("ObjectUtils.notEqual(obj, obj2) ==> " + !ObjectUtils.notEqual(obj, obj2));
            System.out.println("obj.equals(obj3) ==> " + obj.equals(obj3));
            System.out.println("ObjectUtils.notEqual(str, str2) ==> " + !ObjectUtils.notEqual(JsonUtils.toJson(obj), JsonUtils.toJson(obj2)));
        } catch (Exception e) {
           System.err.println(e.getMessage());
           e.printStackTrace();
        }
    }
}
