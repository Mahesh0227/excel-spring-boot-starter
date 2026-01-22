package com.bsh.excel.spring.boot.starter.core;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelProcessor {

    public List<String> readColumnByHeader(InputStream is, String headerName) throws Exception {

        List<String> values = new ArrayList<>();

        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);

        Row headerRow = sheet.getRow(0);
        int targetCol = -1;

        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().equalsIgnoreCase(headerName)) {
                targetCol = cell.getColumnIndex();
                break;
            }
        }

        if (targetCol == -1) {
            throw new RuntimeException("Column not found: " + headerName);
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell cell = row.getCell(targetCol);
                if (cell != null) {
                    values.add(cell.toString());
                }
            }
        }

        workbook.close();
        return values;
    }
}
