package com.picbel.collectmap.fixture;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class MethodSourceFactory {
    public static final String PROVIDE_KML_PATH_AND_TAG = "com.picbel.collectmap.fixture.MethodSourceFactory#provideKmlPathAndTag";

    public static Stream<Arguments> provideKmlPathAndTag() {
        return Stream.of(Arguments.of("src/main/resources/sample/sample_map_night.kml", "Placemark"));
    }
}
