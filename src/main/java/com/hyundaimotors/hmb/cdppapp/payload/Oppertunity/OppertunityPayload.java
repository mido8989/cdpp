package com.hyundaimotors.hmb.cdppapp.payload.Oppertunity;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class OppertunityPayload {
    
    @Schema(name = "OppertunityPayload.Request")
    @Getter
    @Setter
    public static class Request{
        private String accountExternalId;
        private String contentTag;
        private String lossReason;
        private String saleSqualifiedDate;
        private String salesStatus;
        private String closeDate;
        private String consultendDate;
        private String autonumberForName;
        private String bodyType;
        private String lostReasonDescription;
        private String accountId;
        private String transferredOpportunityDate;
        private String dealer;
        private String name;
        private String consultFixedDate;
        private String acceptedDate;
        private String predictiveTemp;
        private String dealerAccount;
        private String dmsOpportunityName;
        private String salesOpportunityCode;
        private String dealerId;
        private String level4;
        private String level3;
        private String level2;
        private String contactDate;
        private String level1;
        private String createdDate;
        private String consultNo;
        private String transferredOpportunity;
        private String companionSalesName;
        private String testDriveProtocolid;
        private String closeStatus;
        private String campaignTag;
        private String companionSalesnumber;
        private String lostReason;
        private String completeDate;
        private String description;
        private String dcxtOpportunityKey;
        private String mediumTag;
        private String budGet;
        private String budgetConfirmed;
        private String reason;
        private String sfid;
        private String id;
        private String sourceTag;
        private String stageName;
        private String opportunityType;
        private String dealerCode;
        private String origin;
        private String form;
        private String externalId;
        private String contact;
        private String contactid;
        private String pcd;
        private String lastModifiedDate;
        private String qexpertTransferredDate;
        private String qexperTreceivedDate;
        private String prioriDade;
        private String recordTypeId;
        private String subsidiaryCode;
        private String loststage;
        private String isautoClosedLost;
        private String additionalNote;
    }

    @Schema(name = "OppertunityPayload.Response")
    @Getter
    @Setter
    public static class Response{
        private String rowId;
        @JsonProperty("Error_spcCode")
        private String errorSpcCode;
        @Schema(description = "errorSpcMessage", example = "OK")
        @JsonProperty("Error_spcMessage")
        private String errorSpcMessage;
    }
}
