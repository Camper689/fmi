package com.fmi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class DepartmentSection {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    private DepartmentSection section;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "section")
    private List<DepartmentSection> subSections = new ArrayList<>();

    @OneToMany(mappedBy = "section")
    private List<DepartmentPage> pages = new ArrayList<>();
}
