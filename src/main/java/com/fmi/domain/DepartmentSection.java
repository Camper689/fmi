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

    private String name;

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    private List<DepartmentPage> pages = new ArrayList<>();
}
