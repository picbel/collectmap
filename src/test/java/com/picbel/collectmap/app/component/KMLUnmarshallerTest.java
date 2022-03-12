package com.picbel.collectmap.app.component;

import com.picbel.collectmap.app.model.GooglePlaceMark;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.w3c.dom.NodeList;

import java.util.List;

import static com.picbel.collectmap.fixture.MethodSourceFactory.PROVIDE_KML_PATH_AND_TAG;
import static org.assertj.core.api.Assertions.assertThat;

class KMLUnmarshallerTest {

    @ParameterizedTest
    @MethodSource(PROVIDE_KML_PATH_AND_TAG)
    void unmarshal(String kmlPath, String tag) {
        //given
        KMLReader reader = new KMLReader();
        NodeList nodeList = reader.parse(kmlPath, tag);

        KMLUnmarshaller unmarshaller = new KMLUnmarshaller();

        //when
        List<GooglePlaceMark> unmarshal = unmarshaller.unmarshal(nodeList, GooglePlaceMark.class);

        //then
        assertThat(unmarshal.size()).isEqualTo(nodeList.getLength());
    }
}