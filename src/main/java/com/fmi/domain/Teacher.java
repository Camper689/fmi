package com.fmi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class Teacher {

    public static final int INFO_MAX_LENGTH = 2000;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private TeacherStatus status;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Department department;

    @OneToMany(mappedBy = "teacher")
    private Set<Timetable> timetables = new LinkedHashSet<>();

    private String firstName;

    private String middleName;

    private String lastName;

    @Column(length = INFO_MAX_LENGTH)
    private String info;

    @OneToOne
    private Image avatar;

    public boolean hasAvatar() {
        return avatar != null;
    }

    public String getShortName() {
        return String.format("%s %s. %s.", lastName, firstName.charAt(0), middleName.charAt(0));
    }

    public String getFullName() {
        return String.join(" ", lastName, firstName, middleName);
    }

    public boolean hasStatus() {
        return status != null;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", status=" + status +
                ", department=" + (department == null ? "null" : department.getId()) +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
