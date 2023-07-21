package com.hyundaimotors.hmb.cdppapp.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class s_contact_xmDto {    
    private String row_id;
    private String created;
    private String created_by;
    private String last_upd;
    private String last_upd_by;
    private String modification_num;
    private String conflict_id;
    private String par_row_id;
    private String name;
    private String type;
    private String attrib_08;
    private String attrib_09;
    private String attrib_10;
    private String attrib_11;
    private String attrib_12;
    private String attrib_13;
    private int attrib_14;
    private String attrib_15;
    private String attrib_16;
    private String attrib_17;
    private String attrib_18;
    private String attrib_19;
    private String attrib_20;
    private String attrib_21;
    private String attrib_22;
    private String attrib_23;
    private String attrib_24;
    private String attrib_25;
    private String attrib_26;
    private String attrib_27;
    private String attrib_28;
    private String attrib_29;
    private String attrib_30;
    private String attrib_31;
    private String attrib_32;
    private String attrib_33;
    private String db_last_upd;
    private String attrib_01;
    private String attrib_02;
    private String attrib_03;
    private String attrib_04;
    private String attrib_05;
    private String attrib_06;
    private String attrib_07;
    private String attrib_34;
    private String attrib_35;
    private String attrib_36;
    private String attrib_37;
    private String attrib_38;
    private String attrib_39;
    private String attrib_40;
    private String attrib_41;
    private String attrib_42;
    private String attrib_43;
    private String attrib_44;
    private String attrib_45;
    private String attrib_46;
    private String attrib_47;
    private String db_last_upd_src;
    private String x_attrib_47;
    private String x_attrib_48;
    private String x_attrib_49;
    private String x_attrib_50;
    private String x_app_start_date;
    private String x_app_status;
    private String x_children_birth_date;
    private String x_soc_med_join_date;
    private String x_soc_med_like_hmb_flg;
    private String x_soc_med_user;
    private String x_vehicle_intent_change;
    private String x_vehicle_year_model;
    private String x_is_current_flg;
    private String x_model;
    private String x_name;
    private String x_license_plate;
    private String x_main_drive_prop;
    private String x_vehicle_color;
    private String x_source_city;
    private String x_day_name;
    private String x_dealer_id;
    private int x_vehicle_cli_expec_val;
}
