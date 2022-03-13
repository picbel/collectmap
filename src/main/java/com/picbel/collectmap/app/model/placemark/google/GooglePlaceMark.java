package com.picbel.collectmap.app.model.placemark.google;

import com.picbel.collectmap.app.model.placemark.PlaceMark;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "Placemark")
@XmlAccessorType(XmlAccessType.FIELD)
public class GooglePlaceMark implements PlaceMark {

    private String group;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "Point")
    private Point point;

    @XmlElement(name = "ExtendedData")
    private ExtendedData extendedData;

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String coordinate() {
        return point.getCoordinates().strip();
    }

    @Override
    public String coordinateX() {
        return point.getCoordinates()
                .split(",")[0]
                .strip();
    }

    @Override
    public String coordinateY() {
        return point.getCoordinates()
                .split(",")[1]
                .strip();
    }
}
