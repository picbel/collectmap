package com.picbel.collectmap.app.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "Placemark")
@XmlAccessorType(XmlAccessType.FIELD)
public class GooglePlaceMark implements PlaceMark {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "Point")
    private Point point;

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
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
