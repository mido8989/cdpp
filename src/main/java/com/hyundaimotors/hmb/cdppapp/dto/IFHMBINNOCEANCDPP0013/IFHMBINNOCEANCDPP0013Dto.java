package com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0013;

import java.util.List;

import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Adress;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Contact;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013PartnerType;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0013.IFHMBINNOCEANCDPP0013Position;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IFHMBINNOCEANCDPP0013Dto {
    private String integrationId;
    private int rowId;
    private String cnpj;
    private String name;
    private String nickname;
    private String partnerFlag;
    private String reasonStatus;
    private String dealerType;
    private String dealerCode;
    private String location;
    private String mainPhone;
    private String fax;
    private String mainEmailAddress;
    private String workEmailAddress;
    private String salesDealerFlag;
    private String serviceDealerFlag;
    private String partnerStartDate;
    private String partnerEndDate;
    private String region;
    private String workingHour;
    private String facebookPage;
    private String homepage;
    private String ErrorSpcCode;
    private String ErrorSpcMessage;
    List<IFHMBINNOCEANCDPP0013Adress> addressList;
    List<IFHMBINNOCEANCDPP0013Contact> contactList;
    List<IFHMBINNOCEANCDPP0013PartnerType> partnerTypeList;
    List<IFHMBINNOCEANCDPP0013Position> positionList;
}
