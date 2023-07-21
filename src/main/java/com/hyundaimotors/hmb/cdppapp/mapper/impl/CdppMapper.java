package com.hyundaimotors.hmb.cdppapp.mapper.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import com.hyundaimotors.hmb.cdppapp.dto.s_contactDto;
import com.hyundaimotors.hmb.cdppapp.dto.s_contact_xDto;
import com.hyundaimotors.hmb.cdppapp.dto.s_contact_xmDto;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowCarPayLoad;
import com.hyundaimotors.hmb.cdppapp.payload.InboundContactWorkflowPayLoad;

public class CdppMapper {
    public int InboundContactWorkflowRequestMap(InboundContactWorkflowPayLoad.Request req, s_contactDto sContactDto, s_contact_xDto sContactXDto, List<s_contact_xmDto> sContactXmDtoList) throws Exception{
        SimpleDateFormat stringformatter = new SimpleDateFormat("yyyy-MM-dd");

        sContactDto.setIntegration_id(req.getIntegrationId());
        sContactDto.setX_cpf(req.getCPF());
        sContactDto.setFst_name(req.getFirstName());
        sContactDto.setLast_name(req.getLastName());  
        sContactDto.setBirth_dt(stringformatter.parse(req.getBirthDate()));
        sContactDto.setSex_mf(req.getGender());
        sContactDto.setHome_ph_num(req.getHomePhone());
        sContactDto.setWork_ph_num(req.getWorkPhone());
        sContactDto.setCell_ph_num(req.getCellPhone());
        sContactDto.setEmail_addr(req.getEmailAddress());
        sContactDto.setPref_comm_meth_cd(req.getWayOfContact());
        sContactDto.setMarital_stat_cd(req.getMaritalStatus());
        sContactDto.setSuppress_call_flg(req.getSuppressAllCalls());
        sContactDto.setSuppress_mail_flg(req.getSuppressAllMailings());
        sContactDto.setSuppress_email_flg(req.getSuppressAllEmails());
        //sContactDto.setSuppress_sms_flg(req.getSuppressAllSMS());
        sContactDto.setX_printed_card(req.getPrintedCard());
        //sContactDto.setX_already_customer(req.getalready_customer());
        
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatedNow = now.format(formatter);
        sContactDto.setCreated(formatedNow);
        sContactDto.setLast_upd(formatedNow);
        sContactDto.setCreated_by("admin_user");
        sContactDto.setLast_upd_by("admin_user");
        
        String rowId = getRandomRowId();

        sContactDto.setRow_id(rowId);  
        
        

        sContactXDto.setRow_id(getRandomRowId());
        sContactXDto.setPar_row_id(rowId);
        sContactXDto.setAttrib_50(req.getSource());
        sContactXDto.setAttrib_34(req.getOccupation());
        sContactXDto.setAttrib_36(req.getIncome());
        sContactXDto.setAttrib_49(req.getCarOfInterest());
        sContactXDto.setX_called_by(req.getCalledBy());
        sContactXDto.setX_literacy(req.getLiteracy());
        sContactXDto.setX_seg_behavioral(req.getBehavioralSegment());
        sContactXDto.setX_seg_value(req.getValueSegment());
        sContactXDto.setX_seg_google(req.getGoogleSegment());
        sContactXDto.setX_seg_facebook(req.getFacebookSegment());
        sContactXDto.setX_seg_life_cycle(req.getLifeCycleSegment());
        sContactXDto.setX_seg_interest(req.getInterestSegment());
        sContactXDto.setX_loyalty_optin_flg(req.getContactOptIn());





        
        
        List<InboundContactWorkflowCarPayLoad> listOfCars = req.getListOfCurrentCars();
        for(int i=0; i<listOfCars.size(); i++){
            s_contact_xmDto sContactXmDto = new s_contact_xmDto();
            sContactXmDto.setRow_id(getRandomRowId());
            sContactXmDto.setPar_row_id(rowId);
            sContactXmDto.setX_name(listOfCars.get(i).getName());
            sContactXmDto.setX_vehicle_year_model(listOfCars.get(i).getYearModel());
            sContactXmDto.setX_vehicle_intent_change(listOfCars.get(i).getIntentionToChange());
            sContactXmDto.setX_is_current_flg(listOfCars.get(i).getIsCurrent());
            sContactXmDto.setX_model(listOfCars.get(i).getCarModel());
            sContactXmDto.setAttrib_14(Integer.valueOf(listOfCars.get(i).getMileage()));
            sContactXmDto.setX_vehicle_cli_expec_val(Integer.valueOf(listOfCars.get(i).getClientExpectedValue()));
            sContactXmDtoList.add(sContactXmDto);
        }


        return 0;
    }

    private String getRandomRowId(){
        Random random = new Random();
        Long randomNumber = random.nextLong();
        if(randomNumber < 0) {
            randomNumber = randomNumber * -1;
        }
        String rowId = String.valueOf(randomNumber);
        if(rowId.length()>15){
            rowId = rowId.substring(0, 15);
        }

        return rowId;
    }
}
