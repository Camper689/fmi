package com.fmi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class Department {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "department")
    private Set<Teacher> teachers = new LinkedHashSet<>();

    @OneToOne
    private Teacher chief;

    @OneToMany(mappedBy = "department")
    private Set<DepartmentPage> pages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "department")
    private Set<DepartmentSection> sections = new LinkedHashSet<>();
}
