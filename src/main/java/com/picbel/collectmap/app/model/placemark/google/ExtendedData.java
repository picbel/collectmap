package com.picbel.collectmap.app.model.placemark.google;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlRootElement(name = "ExtendedData")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExtendedData {

    @XmlElementWrapper(name = "Data")
    @XmlElement(name = "value")
    List<String> values;

}
