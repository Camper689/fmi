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

    @OneToMany
    private Set<Post> posts = new LinkedHashSet<>();

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> images = new LinkedHashSet<>();

    @Transient
    private String lastImage;

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
