package com.example.subway2.controller;

import com.example.subway2.ExcelReader;
import com.example.subway2.StationCode;
import com.example.subway2.entity.SubwayStationInfo;
import com.example.subway2.repository.SubwayStationInfoRepository;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubwayStationInfoController {
    //start_work1()
    private final SubwayStationInfoRepository subwayStationInfoRepository;

    @RequestMapping("/subwayinfo")
    public String save() throws IOException
    {
        int numofTuples = 0;
        String result = " ";
        String filePath = "/Users/seunggyukim/Downloads/운영기관_역사_코드정보_2023.09.26.xlsx";
        ExcelReader excelReader = new ExcelReader();
        List<StationCode> stationCodes = excelReader.readExcelFile(filePath);
        int exceptionTrace = 0;
        try {
            for(StationCode tempIdx : stationCodes)
            {
                //https 를하면 첫날에는 됬는데 handshake오류가난다. https는 보안쪽에서 더 까다로워서.
                String urlStr = "http://openapi.kric.go.kr/openapi/convenientInfo/stationGateInfo?serviceKey=$2a$10$Mj4bgn36lc9Eb1ODdU6fbuQuDHmeNVGNzVF1gcBqnNRNXqLD2onT." +
                        "&format=json" +
                        "&railOprIsttCd=" + tempIdx.getRailOprIsttCd() +
                        "&lnCd=" + tempIdx.getLnCd() + "&stinCd=" + tempIdx.getStinCd();
                URL url = new URL(urlStr);
                BufferedReader br;
                br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));

                result = br.readLine();

                //JSON 파싱 객체를 생성
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(result);

                //item 안쪽의 데이터는 [] 즉 배열의 형태이기에 제이슨 배열로 받아온다.
                JSONArray array = (JSONArray) jsonObject.get("body");

                if(array != null) {
                    for (int i = 0; i < array.size(); i++) {
                        JSONObject tmp = (JSONObject) array.get(i);
                        SubwayStationInfo subwayStationInfo = new SubwayStationInfo(
                                //여기서 pk를 생성자로 설정하면 GenratedValue 어노테이션으로 쓴 의미가없어지고 충돌이나서 쓰지말자.
                                String.valueOf(tmp.get("railOprIsttCd")),
                                String.valueOf(tmp.get("lnCd")),
                                String.valueOf(tmp.get("stinCd")),
                                String.valueOf(tmp.get("exitNo")),
                                String.valueOf(tmp.get("impFaclNm")),
                                String.valueOf(tmp.get("dst")),
                                String.valueOf(tmp.get("adr")),
                                String.valueOf(tmp.get("telNo")));
                                //이거 valueOf를 하는이유는 null이 널말고 string "null"로 들어갈수있기때문에 타입캐스팅 오류가 날수있어서.
                        subwayStationInfoRepository.save(subwayStationInfo);
                        numofTuples++;
                    }
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
