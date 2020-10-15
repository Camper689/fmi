package com.fmi.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Album album;

    @Column(length = 500)
    private String img;

    @Column(length = 10000)
    private String body;
}
