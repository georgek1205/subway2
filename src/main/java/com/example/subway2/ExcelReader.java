package com.example.subway2;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    public List<StationCode> readExcelFile(String filePath) {
        List<StationCode> stationCodes = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);  // 첫 번째 시트를 가져옵니다.
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;  // 헤더는 건너뜁니다.

                Cell railOprIsttCdCell = row.getCell(0);
                Cell lnCdCell = row.getCell(2);
                Cell stinCdCell = row.getCell(4);

                String railOprIsttCd = railOprIsttCdCell.getStringCellValue();
                String lnCd = lnCdCell.getStringCellValue();
                String stinCd = stinCdCell.getStringCellValue();

                stationCodes.add(new StationCode(railOprIsttCd, lnCd, stinCd));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stationCodes;
    }
}

