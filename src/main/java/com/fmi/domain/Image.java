package com.fmi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Album album;

    private String prefix;

    private String name;

    public String getFullName() {
        return prefix + "__" + name;
    }

    public String getAlt() {
        return name.contains(".") ? name.substring(0, name.indexOf(".")) : name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return prefix.equals(image.prefix) &&
                name.equals(image.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, name);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", album=" + album +
                ", prefix='" + prefix + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
