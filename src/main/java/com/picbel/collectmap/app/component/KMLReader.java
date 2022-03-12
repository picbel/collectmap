package com.picbel.collectmap.app.component;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Optional;

public class KMLReader {
    private DocumentBuilder documentBuilder;

    public KMLReader() {
        try {
            this.documentBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
        } catch (Exception e) {
            // do nothing
        }
    }

    // src/main/resources/sample/sample_map_night.kml
    // Placemark
    public NodeList parse(String kmlPath, String tag) {
        Optional<Document> optionalDocument = parse(kmlPath);
        optionalDocument.ifPresent(o -> o.getDocumentElement().normalize());
        return optionalDocument.map(o -> o.getElementsByTagName(tag))
                .orElseThrow();
    }

    private Optional<Document> parse(String kmlPath) {
        try {
            return Optional.of(documentBuilder.parse(kmlPath));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
