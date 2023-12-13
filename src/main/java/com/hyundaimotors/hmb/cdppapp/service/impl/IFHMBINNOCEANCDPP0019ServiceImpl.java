package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0019Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0019Mapper;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0019Payload.ListOfDays;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBINNOCEANCDPP0019Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IFHMBINNOCEANCDPP0019ServiceImpl implements IFHMBINNOCEANCDPP0019Service{

    private final IFHMBINNOCEANCDPP0019Mapper mapper;

    public List<IFHMBINNOCEANCDPP0019Dto> getList(IFHMBINNOCEANCDPP0019Dto dto)throws Exception{
        List<IFHMBINNOCEANCDPP0019Dto> resultList = new ArrayList<>();

        resultList = mapper.getList(dto);

        /***************** sample **************/
        List<String> daysList = new ArrayList<String>();
        List<String> holyDayList = new ArrayList<>();
        List<String> MaintenanceDayList = new ArrayList<>();

        holyDayList = mapper.getHolyDayList(dto);
        MaintenanceDayList = mapper.getMaintenanceDayList(dto);

        daysList.addAll(holyDayList);
        daysList.addAll(MaintenanceDayList);
        
        //String 날짜만있으면 N
        /*****************************************/

        Calendar cal = Calendar.getInstance();

        int year = Integer.parseInt(dto.getYear());
        int month = Integer.parseInt(dto.getMonth());

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);

        cal.set(year,month-1,1);

        int end = cal.getActualMaximum(Calendar.DATE); //해당 월 마지막 날짜
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //해당 날짜의 요일(1:일요일 … 7:토요일)

        for(int i=1; i<=end; i++) {
            if(i==1) {
                for(int j=1; j<dayOfWeek; j++) {

                }
            }

            if(dayOfWeek%7==0) { //토요일
                int week = dayOfWeek;
            }else if(dayOfWeek%7==1){//일요일
                int day  = dayOfWeek;
            }else{
//                System.out.print(" "+i+" ");
                Map<String,String> holidayMap = distinctMap(daysList);
                String day = i>9?""+i:"0"+i;
                IFHMBINNOCEANCDPP0019Dto resultDto = new IFHMBINNOCEANCDPP0019Dto();
                resultDto.setDescription(day);
                
                resultDto.setAvailable(holidayMap.get(day) == null?"Y":"N");
                resultList.add(resultDto);
            }
            dayOfWeek++;
        }

        return resultList;
    }

    /**
     *
     * @return
     */
    public Map<String,String> distinctMap(List<String> daysList) {
        Map<String,String> result = new HashMap<String,String>();
        for(int index=0;index<daysList.size();index++) result.put(daysList.get(index), "N");
        return result;
    }

    /**
     * sample
     * @return
     */
    public List<String> sampleList01() {
        List<String> result = new ArrayList<String>();

        String[] description = {"06","08","10","14","22","30"};

        for(int index=0;index<description.length;index++) {

            result.add(description[index]);
        }

        return result;
    }

    /**
     * sample
     * @return
     */
    public List<String> sampleList02() {
        List<String> result = new ArrayList<String>();

        String[] description = {"07","08","11","14","23","29"};

        for(int index=0;index<description.length;index++) {
        	result.add(description[index]);
        }

        return result;
    }
}
