package com.hyundaimotors.hmb.cdppapp.output;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBDMSCDPP0004.GetLeadQuExpertDto;
import com.hyundaimotors.hmb.cdppapp.dto.IFHMBRECLAMEAQUICDPP0096.IFHMBRECLAMEAQUICDPP0096Dto;
import com.hyundaimotors.hmb.cdppapp.payload.IFHMBDMSCDPP0004.GetLeadQuExpert;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBDMSCDPP0004Service;
import com.hyundaimotors.hmb.cdppapp.service.IFHMBRECLAMEAQUICDPP0096Service;

@EnableScheduling
@Component
public class GetLeadBatch {
    
    @Autowired
    private IFHMBDMSCDPP0004Service service;

    ModelMapper modelMapper = new ModelMapper();

    
    @Scheduled(cron = "0 */5 * * * *")
    public void getQuExpert() throws ParseException{
        System.out.println("Start =========================================> ");
        String accessToken = getToken();

        System.out.println("accessToken =========================================> " + accessToken);
        List<GetLeadQuExpertDto> quexpertList = new ArrayList<>();

        quexpertList = service.getQuexpertList();
        try {
            if(0 < quexpertList.size()){
                System.out.println("quexpertList =========================================> " + quexpertList.size());
                for(int i=0; i < quexpertList.size(); i++){
                    GetLeadQuExpertDto dto = new GetLeadQuExpertDto();
                    dto = quexpertList.get(i);
                    ObjectMapper mapper = new ObjectMapper(); 
                    GetLeadQuExpert payload = new GetLeadQuExpert();
                    payload = modelMapper.map(dto, GetLeadQuExpert.class);
                    String jsonString = mapper.writeValueAsString(payload);
                    
                    System.out.println("result =========================================> " + jsonString);
                    
                    // REST API 호출 및 데이터 처리 로직
                    HttpClient client = HttpClient.newHttpClient();
                    
                    // REST API 엔드포인트 URL 설정
                    String apiUrl = "https://api.hyundai-brasil.com:8065/integration/q-expert/leadscore/v1.0/score-lead";

                    // HTTP 요청 초기화
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(apiUrl))
                            .setHeader("Authorization", "Bearer " + accessToken)
                            .header("Content-Type", "text/plain")
                            .POST(BodyPublishers.ofString(jsonString))
                            .build();

                    service.updateTransQu(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      
                
    //GetLeadQuExpertDto
    }

    public String getToken() throws ParseException{
        HttpClient client = HttpClient.newHttpClient();
        String endPoint = "https://api.hyundai-brasil.com:8065/integration/v1/oauth/token";
        String params =  "client_id=b51a5b65-1363-4bf1-8015-0707d7f9b8e2&client_secret=4cf4514e-663e-478a-b4eb-ecedccc82aaa&grant_type=client_credentials";
        HttpResponse<String> response;
        String responseBody = "";
        String accessToken = "";
        try {
        
        BodyPublisher body = BodyPublishers.ofString(params);


        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endPoint))
                .setHeader("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .POST(body)
                .build();
        
        
        
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        int statusCode = response.statusCode();

        responseBody = response.body();
        
     
        JSONObject jsonObj = (JSONObject) new JSONParser().parse(responseBody);

        accessToken = String.valueOf(jsonObj.get("access_token"));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
        return accessToken;
    }
}
