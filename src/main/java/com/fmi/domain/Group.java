package com.fmi.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "grp")
@Data
public class Group {

    @Id
    @GeneratedValue
    private Long id;

    private long course;

    @Column(unique = true)
    private String name;
}
