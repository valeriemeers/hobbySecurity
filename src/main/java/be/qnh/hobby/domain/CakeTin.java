package be.qnh.hobby.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class CakeTin implements Serializable {
    private static final long serialVersionID = 56498795462246L;    //heeft niets te maken met Spring Boot of Spring

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String material;
    private String colour;
    private String shape;

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }


    @Override
    public String toString() {
        return "CakeTin" +
                "material='" + material + "'" +
                ", colour='" + colour + "'" +
                ", shape='" + shape + "'";
    }
}
