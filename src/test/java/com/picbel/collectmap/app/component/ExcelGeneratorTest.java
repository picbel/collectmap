package com.picbel.collectmap.app.component;

import com.picbel.collectmap.app.model.placemark.google.GooglePlaceMark;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.w3c.dom.NodeList;

import java.util.List;

import static com.picbel.collectmap.fixture.MethodSourceFactory.PROVIDE_KML_PATH_AND_TAG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ExcelGeneratorTest {

    @ParameterizedTest
    @MethodSource(PROVIDE_KML_PATH_AND_TAG)
    void toExcel(String kmlPath, String tag) {
        //given
        KMLReader reader = new KMLReader();
        NodeList nodeList = reader.parse(kmlPath, tag);
        KMLUnmarshaller unmarshaller = new KMLUnmarshaller();
        List<GooglePlaceMark> googlePlaceMarkList = unmarshaller.unmarshal(nodeList, GooglePlaceMark.class);

        //when
        ExcelGenerator excelGenerator = new ExcelGenerator();
        boolean b = excelGenerator.toExcel(googlePlaceMarkList, "구글_맵_테스트");

        //then
        assertThat(b).isTrue();
    }
}