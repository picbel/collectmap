package com.picbel.collectmap.test;

import com.picbel.collectmap.app.model.GooglePlaceMark;
import com.picbel.collectmap.app.component.KMLReader;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
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
}

