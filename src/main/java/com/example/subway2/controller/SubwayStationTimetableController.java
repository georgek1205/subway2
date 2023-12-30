package com.example.subway2.controller;

import com.example.subway2.ExcelReader;
import com.example.subway2.StationCode;
import com.example.subway2.entity.SubwayStationTimetable;
import com.example.subway2.repository.SubwayStationTimetableRepository;
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
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubwayStationTimetableController {
    //start_work4()
    public final SubwayStationTimetableRepository subwayStationTimetableRepository;

    @RequestMapping("/station-timetable")
    public String save() throws IOException
    {
        int numofTuples = 0;
        String result = " ";
        String filePath = "/Users/seunggyukim/Downloads/운영기관_역사_코드정보_2023.09.26.xlsx";
        ExcelReader excelReader = new ExcelReader();
        List<StationCode> stationCodes = excelReader.readExcelFile(filePath);
        List<String> dayCd = Arrays.asList("8", "7", "9");
        int exceptionTrace = 0;
        try {
            for(StationCode tempIdx : stationCodes)
            {
                for(String day : dayCd) {
                    //https 를하면 첫날에는 됬는데 handshake오류가난다. https는 보안쪽에서 더 까다로워서.
                    String urlStr = "http://openapi.kric.go.kr/openapi/convenientInfo/stationTimetable?serviceKey=$2a$10$Mj4bgn36lc9Eb1ODdU6fbuQuDHmeNVGNzVF1gcBqnNRNXqLD2onT." +
                            "&format=json" +
                            "&railOprIsttCd=" + tempIdx.getRailOprIsttCd() +
                            "&lnCd=" + tempIdx.getLnCd() + "&stinCd=" + tempIdx.getStinCd()
                            + "&dayCd=" + day;
                    URL url = new URL(urlStr);
                    BufferedReader br;
                    br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

                    result = br.readLine();

                    //JSON 파싱 객체를 생성
                    JSONParser jsonParser = new JSONParser();
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(result);

                    //item 안쪽의 데이터는 [] 즉 배열의 형태이기에 제이슨 배열로 받아온다.
                    JSONArray array = (JSONArray) jsonObject.get("body");

                    if (array != null) {
                        for (int i = 0; i < array.size(); i++) {
                            JSONObject tmp = (JSONObject) array.get(i);
                            SubwayStationTimetable subwayStationTimeTable = new SubwayStationTimetable(
                                    String.valueOf(tmp.get("railOprIsttCd")),
                                    String.valueOf(tmp.get("trnNo")),
                                    String.valueOf(tmp.get("dayCd")),
                                    String.valueOf(tmp.get("dayNm")),
                                    String.valueOf(tmp.get("stinCd")),
                                    String.valueOf(tmp.get("lnCd")),
                                    String.valueOf(tmp.get("arvTm")),
                                    String.valueOf(tmp.get("dptTm")),
                                    String.valueOf(tmp.get("orgStinCd")),
                                    String.valueOf(tmp.get("tmnStinCd")));
                            subwayStationTimetableRepository.save(subwayStationTimeTable);
                            numofTuples++;
                        }
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
