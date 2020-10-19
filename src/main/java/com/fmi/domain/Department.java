package com.fmi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Department {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 300)
    private String name;

    @OneToMany(mappedBy = "department")
    private Set<Teacher> teachers = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.EAGER)
    private Teacher chief;

    @OneToMany(mappedBy = "department")
    private Set<DepartmentPage> pages = new TreeSet<>(Comparator.comparing(DepartmentPage::getTitle));

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private Set<DepartmentSection> sections = new TreeSet<>(Comparator.comparing(DepartmentSection::getId));

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean hasChief() {
        return chief != null;
    }
}