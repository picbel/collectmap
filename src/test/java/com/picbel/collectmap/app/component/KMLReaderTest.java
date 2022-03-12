package com.picbel.collectmap.app.component;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.w3c.dom.NodeList;

import static com.picbel.collectmap.fixture.MethodSourceFactory.PROVIDE_KML_PATH_AND_TAG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class KMLReaderTest {
    @ParameterizedTest
    @MethodSource(PROVIDE_KML_PATH_AND_TAG)
    void KML_경로와_TAG를_입력하면_NodeList가_반환된다(String kmlPath, String tag) throws Exception {
        //given
        KMLReader reader = new KMLReader();

        //when
        NodeList nodeList = reader.parse(kmlPath, tag);

        //then
        assertAll(
                () -> assertThat(nodeList).isNotNull(),
                () -> assertThat(nodeList.getLength()).isEqualTo(156)
        );
    }
}