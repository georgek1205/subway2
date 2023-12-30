package com.example.subway2.controller;

import com.example.subway2.entity.RealTimeStationArrivalInfo;
import com.example.subway2.repository.RealTimeStationArrivalRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@RestController
@RequiredArgsConstructor
public class RealTimeStationArrivalController {

    private final RealTimeStationArrivalRepository realTimeStationArrivalRepository;

    @RequestMapping("/realtime-train")
    public String save() throws IOException
    {
        int numofTuples = 0;
        String result = " ";
        int exceptionTrace = 0;
        try {
            String urlStr = "http://swopenAPI.seoul.go.kr/api/subway/696c73416a67656f3637636b584a46/json/realtimeStationArrival/ALL";
            URL url = new URL(urlStr);
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));

            result = br.readLine();

            //JSON 파싱 객체를 생성
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);

            //item 안쪽의 데이터는 [] 즉 배열의 형태이기에 제이슨 배열로 받아온다.
            //여기도 뎁스가 코레일 api들처럼 싱글뎁스고, "body" 대신 "realtimeArrivalList" 여서 바로 가져올수있다.
            //postman으로 뎁스 항상 확인.
            JSONArray array = (JSONArray) jsonObject.get("realtimeArrivalList");

            if(array != null) {
                for (int i = 0; i < array.size(); i++) {
                    JSONObject tmp = (JSONObject) array.get(i);
                    RealTimeStationArrivalInfo realTimeStationArrivalInfo = new RealTimeStationArrivalInfo(
                            String.valueOf(tmp.get("subwayId")),
                            String.valueOf(tmp.get("subwayNm")),
                            String.valueOf(tmp.get("updnLine")),
                            String.valueOf(tmp.get("trainLineNm")),
                            String.valueOf(tmp.get("subwayHeading")),
                            String.valueOf(tmp.get("statnFid")),
                            String.valueOf(tmp.get("statnTid")),
                            String.valueOf(tmp.get("statnId")),
                            String.valueOf(tmp.get("statnNm")),
                            String.valueOf(tmp.get("trainCo")),
                            String.valueOf(tmp.get("trnsitCo")),
                            String.valueOf(tmp.get("ordkey")),
                            String.valueOf(tmp.get("subwayList")),
                            String.valueOf(tmp.get("statnList")),
                            String.valueOf(tmp.get("btrainSttus")),
                            String.valueOf(tmp.get("barvlDt")),
                            String.valueOf(tmp.get("btrainNo")),
                            String.valueOf(tmp.get("bstatnId")),
                            String.valueOf(tmp.get("bstatnNm")),
                            String.valueOf(tmp.get("recptnDt")),
                            String.valueOf(tmp.get("arvlMsg2")),
                            String.valueOf(tmp.get("arvlMsg3")),
                            String.valueOf(tmp.get("arvlCd")));
                            realTimeStationArrivalRepository.save(realTimeStationArrivalInfo);
                    numofTuples++;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            exceptionTrace++;
        }
        System.out.println(numofTuples + "tuples created");
        System.out.println("exception occured : " + exceptionTrace);
        return "index";
    }

}
