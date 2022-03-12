package com.picbel.collectmap.app.model;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "Point")
@XmlAccessorType(XmlAccessType.FIELD)
public class Point {
    @XmlElement(name ="coordinates")
    private String coordinates;
}