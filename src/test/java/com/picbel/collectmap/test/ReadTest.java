package com.picbel.collectmap.test;

import com.picbel.collectmap.app.model.placemark.google.GooglePlaceMark;
import com.picbel.collectmap.app.component.KMLReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ReadTest {
    @Test
    void readTest() throws Exception {

        // 언마샬러한테 NodeList랑 매핑할 Java 클래스를 넘겨주면 매핑된 자바 List가 반환된다
        JAXBContext context = JAXBContext.newInstance(GooglePlaceMark.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        String kmlPath = "src/main/resources/sample/sample_map_night.kml";
        String tag = "Placemark";

        KMLReader reader = new KMLReader();
        NodeList nodeList = reader.parse(kmlPath, tag);

        List<GooglePlaceMark> sampleList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            JAXBElement<GooglePlaceMark> element = unmarshaller.unmarshal(item, GooglePlaceMark.class);
            sampleList.add(element.getValue());
        }

        sampleList.forEach(System.out::println);
    }

    @Test
    void excelTest() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Google map");

        Row titleRow = sheet.createRow(0);
        for (int i = 0; i < 10; i++) {
            Cell titleCell = titleRow.createCell(i);
            titleCell.setCellValue("cell value = "+i);
        }


        File currDir = new File(".");                // 현재 프로젝트 경로를 가져옴
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";    // 파일명 설정


        FileOutputStream fileOutputStream = new FileOutputStream(fileLocation);        // 파일 생성
        workbook.write(fileOutputStream);                                            // 엑셀파일로 작성
        workbook.close();
    }
}

