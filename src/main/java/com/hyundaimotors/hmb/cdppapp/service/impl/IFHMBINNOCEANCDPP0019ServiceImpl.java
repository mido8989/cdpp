package com.hyundaimotors.hmb.cdppapp.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0019Dto;
import com.hyundaimotors.hmb.cdppapp.mapper.IFHMBINNOCEANCDPP0019Mapper;
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
        
        //sample week
        List<String> weekDayList = new ArrayList<>();

        holyDayList = mapper.getHolyDayList(dto);
        MaintenanceDayList = mapper.getMaintenanceDayList(dto);
        
        //sample week 처리 부분
        weekDayList = mapper.getSampleWeekDay(dto);
        daysList.addAll(weekDayList);
        //////////////////////////////////
        
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
        
        String nowDate  = nowDate();//년월일(오늘)
        String getYear  = dto.getYear();
        String getMonth = month>9?""+month:"0"+month;

        /* 중복 제거 */
        Map<String,String> holidayMap = distinctMap(daysList);
        
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
                String day = i>9?""+i:"0"+i;
                
                if(isNextDay(nowDate, getYear, getMonth, day)) { //오늘 이후만 처리 할수 있다.
                    IFHMBINNOCEANCDPP0019Dto resultDto = new IFHMBINNOCEANCDPP0019Dto();
//                    resultDto.setDescription(day);//=>01
                    resultDto.setDescription(String.valueOf(Integer.parseInt(day)));//=>1
                    
                    resultDto.setAvailable(holidayMap.get(day) == null?"Y":"N");
                    resultList.add(resultDto);
                }else {
                     IFHMBINNOCEANCDPP0019Dto resultDto = new IFHMBINNOCEANCDPP0019Dto();
//                     resultDto.setDescription(day);//=> 01
                     resultDto.setDescription(String.valueOf(Integer.parseInt(day)));//=>1
                     
                     resultDto.setAvailable("N");
                     resultList.add(resultDto);
                }
            }
            dayOfWeek++;
        }

        return resultList;
    }
    
    /**
     * Get Next Day, starting with the first greater than today.
     * @return
     */
    public boolean isNextDay(String nowDate, String year, String month, String day) {
        boolean result = false;
        
        if(Integer.parseInt(year+month+day) > Integer.parseInt(nowDate)) result = true;
        return result;
    }
    
    /**
     * 년월일(오늘)
     */
    public String nowDate() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");  
        String formatedNow = now.format(formatter); 
        
        return formatedNow;
    }

    /**
     *
     * @return
     */
    public Map<String,String> distinctMap(List<String> daysList) {
        Map<String,String> result = new HashMap<String,String>();
        for(int index=0;index<daysList.size();index++) {
            String day = daysList.get(index).trim();
            day = Integer.parseInt(day)>9?""+Integer.parseInt(day):"0"+Integer.parseInt(day);
//            result.put(daysList.get(index), "N");
            result.put(day, "N");
        }
        return result;
    }
}