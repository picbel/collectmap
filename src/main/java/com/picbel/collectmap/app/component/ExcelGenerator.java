package com.picbel.collectmap.app.component;

import com.picbel.collectmap.app.model.placemark.google.GooglePlaceMark;
import com.sun.istack.NotNull;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ExcelGenerator {

    public boolean toExcel(List<GooglePlaceMark> placeMarkList, @NotNull String excelName){

        try{
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = createSheet(workbook,excelName);
            placeMarkListToCell(placeMarkList, sheet);
            writeExcelFile(workbook,excelName);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private void writeExcelFile(Workbook workbook, String excelName) throws IOException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) +excelName+".xlsx";


        FileOutputStream fileOutputStream = new FileOutputStream(fileLocation);
        workbook.write(fileOutputStream);
        workbook.close();
    }

    private Sheet createSheet(Workbook workbook, String excelName) {
        Sheet sheet = workbook.createSheet(excelName);
        Row titleRow = sheet.createRow(0);

        Cell cell0 = titleRow.createCell(0);
        cell0.setCellValue("장소 이름");

        Cell cell1 = titleRow.createCell(1);
        cell1.setCellValue("장소 설명");

        Cell cell2 = titleRow.createCell(2);
        cell2.setCellValue("장소 좌표");

        Cell cell3 = titleRow.createCell(3);
        cell3.setCellValue("추가 정보");
        return sheet;
    }

    private void placeMarkListToCell(List<GooglePlaceMark> placeMarkList, Sheet sheet) {
        int row = 1;
        for (GooglePlaceMark googlePlaceMark : placeMarkList) {
            Row titleRow = sheet.createRow(row);
            row++;

            Cell cell0 = titleRow.createCell(0);
            cell0.setCellValue(googlePlaceMark.getName());

            Cell cell1 = titleRow.createCell(1);
            cell1.setCellValue(googlePlaceMark.getDescription());

            Cell cell2 = titleRow.createCell(2);
            cell2.setCellValue(googlePlaceMark.getPoint().getCoordinates());

            if (Objects.nonNull(googlePlaceMark.getExtendedData())) {
                Cell cell3 = titleRow.createCell(3);
                cell3.setCellValue(googlePlaceMark.getExtendedData().getValues().toString());
            }
        }
    }
}
