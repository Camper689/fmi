package com.fmi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Album {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private final static Comparator<Image> imageSetComparator = Comparator.comparing(Image::getId).reversed();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "album")
    private Set<Image> images = new TreeSet<>(imageSetComparator);

    @OneToOne
    private Image lastImage;

    public boolean isEmpty() {
        return lastImage == null;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastImage=" + (lastImage == null ? null : lastImage.getId()) +
                '}';
    }
}