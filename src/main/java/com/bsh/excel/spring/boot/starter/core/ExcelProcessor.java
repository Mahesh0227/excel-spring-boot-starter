package com.bsh.excel.spring.boot.starter.core;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.*;

public class ExcelProcessor {

    public Map<String, List<String>> read(InputStream is) {

        Map<String, List<String>> result = new LinkedHashMap<>();

        try (Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);

            for (int col = 0; col < headerRow.getLastCellNum(); col++) {

                String heading = headerRow.getCell(col).getStringCellValue();
                List<String> values = new ArrayList<>();

                for (int row = 1; row <= sheet.getLastRowNum(); row++) {
                    Cell cell = sheet.getRow(row).getCell(col);
                    if (cell != null) {
                        values.add(cell.toString());
                    }
                }
                result.put(heading, values);
            }

        } catch (Exception e) {
            throw new RuntimeException("Excel read failed", e);
        }

        return result;
    }
}
