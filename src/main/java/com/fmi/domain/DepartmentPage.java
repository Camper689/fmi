package com.fmi.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class DepartmentPage {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Department department;

    @ManyToOne
    private DepartmentSection section;
}
