package com.picbel.collectmap.app.component;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KMLUnmarshaller {
    public <T> List<T> unmarshal(NodeList nodeList, Class<T> clazz) {
        Unmarshaller unmarshaller = createUnmarshaller(clazz);
        return IntStream.range(0, nodeList.getLength())
                .mapToObj(unmarshal(unmarshaller, nodeList, clazz))
                .map(JAXBElement::getValue)
                .collect(Collectors.toList());
    }

    private <T> Unmarshaller createUnmarshaller(Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            return context.createUnmarshaller();
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    private <T> IntFunction<JAXBElement<T>> unmarshal(Unmarshaller unmarshaller, NodeList nodeList, Class<T> clazz) {
        return trigger -> unmarshal(unmarshaller, nodeList.item(trigger), clazz);
    }

    private <T> JAXBElement<T> unmarshal(Unmarshaller unmarshaller, Node item, Class<T> clazz) {
        try {
            return unmarshaller.unmarshal(item, clazz);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
