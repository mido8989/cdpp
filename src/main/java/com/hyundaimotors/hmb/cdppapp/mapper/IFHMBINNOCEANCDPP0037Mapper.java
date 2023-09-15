package com.hyundaimotors.hmb.cdppapp.mapper;

import java.util.HashMap;
import java.util.List;

import com.hyundaimotors.hmb.cdppapp.dto.IFHMBINNOCEANCDPP0037Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfAutoVehicle;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactHobby;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactSoccerTeam;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.ListOfContactSocialMedia;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBINNOCEANCDPP0037.UpdateContactOutput;

public interface IFHMBINNOCEANCDPP0037Mapper {
    
    public void insertObject(IFHMBINNOCEANCDPP0037Dto dto)throws Exception;

    public void insertAutoVehicle(IFHMBINNOCEANCDPP0037Dto dto)throws Exception;

    public void insertHobby(IFHMBINNOCEANCDPP0037Dto dto)throws Exception;

    public void insertSoccerTeam(IFHMBINNOCEANCDPP0037Dto dto)throws Exception;

    public void insertSocialMedia(IFHMBINNOCEANCDPP0037Dto dto)throws Exception;

    public UpdateContactOutput getUpdateContactOutput(IFHMBINNOCEANCDPP0037Dto dto)throws Exception;

    public List<ListOfAutoVehicle> getListVehicle(IFHMBINNOCEANCDPP0037Dto dto)throws Exception;
    
    public List<ListOfContactHobby> getListHobby(IFHMBINNOCEANCDPP0037Dto dto)throws Exception;

    public List<ListOfContactSoccerTeam> getListSoccerTeam(IFHMBINNOCEANCDPP0037Dto dto)throws Exception;

    public List<ListOfContactSocialMedia> getListSocialMedia(IFHMBINNOCEANCDPP0037Dto dto)throws Exception;

    public void transferProcess(HashMap<String, String> parMap)throws Exception;

    public IFHMBINNOCEANCDPP0037Dto getAcountAndContactId(IFHMBINNOCEANCDPP0037Dto dto)throws Exception;

    public void transferReplica(HashMap<String, String> parMap)throws Exception;
}
