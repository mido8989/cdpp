package com.hyundaimotors.hmb.cdppapp.payload.IFHMBDMSCDPP0004;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetLeadQuExpert {
    @JsonProperty("SALES_DLR_CODE")
    private String salesDlrCode;
    @JsonProperty("SALES_DLR_NICK")
    private String salesDlrNick;
    @JsonProperty("SUP_CELPH")
    private String supCelph;
    @JsonProperty("CITY_CUST")
    private String cityCust;
    @JsonProperty("QUOTE_DEALER")
    private String quoteDealer;
    @JsonProperty("MEDIUM_TAG")
    private String mediumTag;
    @JsonProperty("STATUS_DEALER")
    private String status_dealer;
    @JsonProperty("QUOTE_VERSION")
    private String quoteVersion;
    @JsonProperty("ORIGIN")
    private String origin;
    @JsonProperty("ASGN_DT")
    private String asgnDt;
    @JsonProperty("BIRTH_DT")
    private String birthDt;
    @JsonProperty("CONTACT_TYPE")
    private String contactType;
    @JsonProperty("DESCRIPTION")
    private String description;
    @JsonProperty("SUP_EMAIL")
    private String supEmail;
    @JsonProperty("SALES_DT")
    private String sales_dt;
    @JsonProperty("GENDER")
    private String gender;
    @JsonProperty("SUP_WHATSAPP")
    private String supWhatsapp;
    @JsonProperty("REASON")
    private String reason;
    @JsonProperty("CLOSE_DT")
    private String closeDt;
    @JsonProperty("QUOTE_COLOR")
    private String quoteColor;
    @JsonProperty("QUOTE_DEALER_NICK")
    private String quote_dealer_nick;
    @JsonProperty("OPEN_DT")
    private String open_dt;
    @JsonProperty("FORM")
    private String form;
    @JsonProperty("QUOTE_VERSION_PRICE")
    private String quoteVersionPrice;
    @JsonProperty("RELATED_MODEL")
    private String relatedModel;
    @JsonProperty("DLR_STATE")
    private String dlr_state;
    @JsonProperty("SOURCE_TAG")
    private String sourceTag;
    @JsonProperty("QUOTE_COLOR_PRICE")
    private String quoteColorPrice;
    @JsonProperty("QUOTE_TRANSMISSION")
    private String quoteTransmission;
    @JsonProperty("PROTOCOL")
    private String protocol;
    @JsonProperty("SUP_VIDEOCALL")
    private String supVideocall;
    @JsonProperty("LEVEL1")
    private String level1;
    @JsonProperty("LEVEL2")
    private String level2;
    @JsonProperty("LEVEL3")
    private String level3;
    @JsonProperty("SUP_SMS")
    private String supSms;
    @JsonProperty("STATUS_QUOTE")
    private String status_quote;
    @JsonProperty("DLR_CITY")
    private String dlrCity;
    @JsonProperty("LEVEL4")
    private String level4;
    @JsonProperty("STATE_CUST")
    private String stateCust;
    @JsonProperty("CONTACT_EMAIL")
    private String contactEmail;
    @JsonProperty("QUOTE_FINAL_PRICE")
    private String quoteFinalPrice;
    @JsonProperty("CAMPAIGN_TAG")
    private String campaignTag;
    @JsonProperty("METHOD")
    private String method;
    @JsonProperty("STAGENAME")
    private String stagename;
}
