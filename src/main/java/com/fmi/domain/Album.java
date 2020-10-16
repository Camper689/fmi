package com.fmi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class Album {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> images = new LinkedHashSet<>();

    @Transient
    private String lastImage;

    public boolean isEmpty() {
        return images.isEmpty();
    }

    public String getAlt(String url) {
        url = url.substring(url.lastIndexOf("/") + 1);
        if(url.contains(".")) url = url.substring(0, url.indexOf("."));
        return url;
    }

    @PostLoad
    public void postLoad() {
        if(!images.isEmpty()) {
            Iterator<String> iterator = images.iterator();
            while (iterator.hasNext()) {
                lastImage = iterator.next();
            }
        }
    }
}