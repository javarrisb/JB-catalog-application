package com.musicstore.catalog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@Table(name="label")
public class Label {

    @Id
    @Column(name="label_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

     private String name;

     private String website;

    public Label() {
    }

    public Label(Integer id, String name, String website) {
        this.id = id;
        this.name = name;
        this.website = website;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return Objects.equals(getId(), label.getId()) && Objects.equals(getName(), label.getName()) && Objects.equals(getWebsite(), label.getWebsite());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getWebsite());
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
