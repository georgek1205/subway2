package com.example.subway2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExcelReaderTest {

    @Test
    void testReadExcelFile() {
        String filePath = "/Users/seunggyukim/Downloads/운영기관_역사_코드정보_2023.09.26.xlsx";
        ExcelReader excelReader = new ExcelReader();
        List<StationCode> stationCodes = excelReader.readExcelFile(filePath);

        // 이후에는 stationCodes가 예상한 대로 생성되었는지 검증하는 코드를 작성하시면 됩니다.
        // 예를 들어 stationCodes가 비어있지 않다는 것을 확인하는 테스트를 작성할 수 있습니다.
        System.out.println(stationCodes);
        assertFalse(stationCodes.isEmpty());
    }
}