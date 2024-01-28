package com.example.subway2.controller;


import com.example.subway2.entity.SubwayStationGeomInfo;
import com.example.subway2.repository.SubwayStationGeomRepository;
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

public class SubwayStationGeomController {

    private final SubwayStationGeomRepository subwayStationGeomRepository;

    @RequestMapping("/subway-geom")
    public String save() throws IOException
    {
        int numofTuples = 0;
        String result = " ";
        int exceptionTrace = 0;
        try {
            String urlStr = "https://t-data.seoul.go.kr/apig/apiman-gateway/tapi/" +
                    "TaimsKsccDvSubwayStationGeom/1.0?apikey=cc2ec515-f833-422d-9ecc-6c9c345fa5ca";

            //HttpURLConnection는 HTTP 메소드(GET, POST 등)를 명시적으로 설정할 수 있습니다.
            //URL의 openStream 메소드는 간단한 GET 요청을 보내는 데 사용됩니다.
            URL url = new URL(urlStr);
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));

            result = br.readLine();
            //JSON 파싱 객체를 생성
            JSONParser jsonParser = new JSONParser();
            JSONArray array = (JSONArray) jsonParser.parse(result);

            if(array != null) {
                for (int i = 0; i < array.size(); i++) {
                    JSONObject tmp = (JSONObject) array.get(i);
                    SubwayStationGeomInfo subwayStationGeomInfo = new SubwayStationGeomInfo(
                            String.valueOf(tmp.get("outStnNum")),
                            String.valueOf(tmp.get("stnKrNm")),
                            String.valueOf(tmp.get("lineNm")),
                            String.valueOf(tmp.get("convX")),
                            String.valueOf(tmp.get("convY")));
                    subwayStationGeomRepository.save(subwayStationGeomInfo);
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
