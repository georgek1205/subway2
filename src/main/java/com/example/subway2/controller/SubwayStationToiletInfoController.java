package com.example.subway2.controller;

import com.example.subway2.ExcelReader;
import com.example.subway2.StationCode;
import com.example.subway2.entity.SubwayStationToiletInfo;
import com.example.subway2.repository.SubwayStationToiletInfoRepository;
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
public class SubwayStationToiletInfoController {
    //start_work2()
    private final SubwayStationToiletInfoRepository subwayStationToiletInfoRepository;

    @RequestMapping("/toilet")
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
                String urlStr = "http://openapi.kric.go.kr/openapi/convenientInfo/stationToilet?serviceKey=$2a$10$Mj4bgn36lc9Eb1ODdU6fbuQuDHmeNVGNzVF1gcBqnNRNXqLD2onT." +
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
                        SubwayStationToiletInfo subwayStationToiletInfo = new SubwayStationToiletInfo(
                                String.valueOf(tmp.get("railOprIsttCd")),
                                String.valueOf(tmp.get("lnCd")),
                                String.valueOf(tmp.get("stinCd")),
                                String.valueOf(tmp.get("grndDvNm")),
                                String.valueOf(tmp.get("stinFlor")),
                                String.valueOf(tmp.get("gateInotDvNm")),
                                String.valueOf(tmp.get("exitNo")),
                                String.valueOf(tmp.get("dtlLoc")),
                                String.valueOf(tmp.get("mlFmlDvNm")),
                                String.valueOf(tmp.get("toltNum")),
                                String.valueOf(tmp.get("diapExchNum")));
                        //이거 valueOf를 하는이유는 null이 널말고 string "null"로 들어갈수있기때문에 타입캐스팅 오류가 날수있어서.
//                        System.out.println(
//                                subwayStationToiletInfo.getOpenApiId() + " " +
//                                subwayStationToiletInfo.getRailOprIsttCd() + " " +
//                                        subwayStationToiletInfo.getLnCd() + " " +
//                                        subwayStationToiletInfo.getStinCd() + " " +
//                                        subwayStationToiletInfo.getGrndDvNm() + " " +
//                                        subwayStationToiletInfo.getStinFlor() + " " +
//                                        subwayStationToiletInfo.getGateInotDvNm() + " " +
//                                        subwayStationToiletInfo.getExitNo() + " " +
//                                        subwayStationToiletInfo.getDtlLoc() + " " +
//                                        subwayStationToiletInfo.getMlFmlDvNm() + " " +
//                                        subwayStationToiletInfo.getToltNum() + " " +
//                                        subwayStationToiletInfo.getDiapExchNum()
//                        );
                        subwayStationToiletInfoRepository.save(subwayStationToiletInfo);
                        numofTuples++;
                        //SubwayStationInfoRepository 말고 이 컨트롤러에 대응하는 새로운 repository를 만들어줘여한다.
                        //ORM기술은 객체를 관계형 데이터베이스에다 저장할수있게 매핑해주는 기술, 마치 sql처럼
                        //1 엔티티 클래스 = 1 테이블. 각 컨트롤러당 저장하는 엔티티클래스가 다르기때문에 엔티티클래스를 저장할수있는
                        // 새로운 리포지토리(테이블)를 생성해야한다.
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
