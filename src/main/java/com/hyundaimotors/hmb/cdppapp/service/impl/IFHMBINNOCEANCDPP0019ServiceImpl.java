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
        
        List<String> daysList = new ArrayList<String>();
        List<String> holyDayList = new ArrayList<>();
        List<String> MaintenanceDayList = new ArrayList<>();
        List<String> weekDayList = new ArrayList<>();

        holyDayList        = mapper.getHolyDayList(dto);
        MaintenanceDayList = mapper.getMaintenanceDayList(dto);
        weekDayList        = mapper.getWeekDayList(dto);
        
        daysList.addAll(holyDayList);
        daysList.addAll(MaintenanceDayList);
        
        Calendar cal = Calendar.getInstance();

        int year  = Integer.parseInt(dto.getYear());
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
        Map<String,String> holidayMap = distinctMap(daysList,weekDayList);
        
        for(int i=1; i<=end; i++) {
            String day = i>9?""+i:"0"+i;
            IFHMBINNOCEANCDPP0019Dto resultDto = new IFHMBINNOCEANCDPP0019Dto();
            resultDto.setDescription(String.valueOf(Integer.parseInt(day)));
            
            int nextResult = isNextDay(nowDate, getYear, getMonth, day, dayOfWeek);
            
            switch(nextResult) {
            case 0://오늘 이전일 처리
                resultDto.setAvailable("N");
                break;
            case 1://주말 처리
                resultDto.setAvailable(holidayMap.get(day) == null?"N":holidayMap.get(day));
                break;
            case 2://나머지 처리
                resultDto.setAvailable(holidayMap.get(day) == null?"Y":holidayMap.get(day));
                break;
                default://그밖에
                    resultDto.setAvailable("N");
                    break;
            }
            resultList.add(resultDto);
            dayOfWeek++;
        }
        
        return resultList;
    }
    
    /**
     * Get Next Day, starting with the first greater than today.
     * @return
     */
    public int isNextDay(String nowDate, String year, String month, String day, int dayOfWeek) {
        int result = 0;
        
        if(Integer.parseInt(year+month+day) < Integer.parseInt(nowDate)) result = 0;
        else if(dayOfWeek%7==0 || dayOfWeek%7==1) result = 1;
        else result = 2;
        
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
   public Map<String,String> distinctMap(List<String> daysList,List<String> weekList) {
       Map<String,String> result  = new HashMap<String,String>();
       
       for(int index=0;index<daysList.size();index++) {
           String day = daysList.get(index).trim();
           day = Integer.parseInt(day)>9?""+Integer.parseInt(day):"0"+Integer.parseInt(day);
           result.put(day, "N");
       }
       
       for(int index=0;index<weekList.size();index++) {
           String day = weekList.get(index).trim();
           day = Integer.parseInt(day)>9?""+Integer.parseInt(day):"0"+Integer.parseInt(day);
           result.put(day, "Y");
       }
       return result;
   }
}