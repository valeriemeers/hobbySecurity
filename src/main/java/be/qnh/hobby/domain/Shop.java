package be.qnh.hobby.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;


public class Shop implements Serializable {
    private static final long serialVersionUID = 5465413218L;

    @Value("${shop.name}")      //krijgt prioriteit over de setter. De waarde in application.properties wordt gekozen
    private String name;
    private String city;

    public String getName() {
        return name;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
