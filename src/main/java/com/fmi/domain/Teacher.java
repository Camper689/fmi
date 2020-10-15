package com.fmi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class Teacher {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private TeacherStatus status;

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "teacher")
    private Set<Timetable> timetables = new LinkedHashSet<>();

    private String firstName;

    private String middleName;

    private String lastName;

    private String avatar;

    public String getShortName() {
        return String.join(" ", lastName, firstName.substring(0, 1) + ".", middleName.substring(0, 1) + ".");
    }

    public String getFullName() {
        return String.join(" ", lastName, firstName, middleName);
    }
}
