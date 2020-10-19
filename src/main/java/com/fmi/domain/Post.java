package com.fmi.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Category category;

    @OneToOne(fetch = FetchType.EAGER)
    private Image image;

    @Column(length = 300)
    private String title;

    @Column(length = 10000)
    private String body;

    private LocalDate date;

    private boolean visible = false;
}
