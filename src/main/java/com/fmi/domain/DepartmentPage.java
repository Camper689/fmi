package com.fmi.domain;

import com.fmi.service.TransliteratorUtils;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class DepartmentPage {

    public static final int BODY_MAX_LENGTH = 5000;
    public static final int TITLE_MAX_LENGTH = 300;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Department department;

    @ManyToOne
    private DepartmentSection section;

    // Якщо true і цей пост не має секції (тобто це пост на сторінці кафедри), буде відображено що цей пост від завідуючого кафедрою
    private boolean fromChief = false;

    @Column(length = TITLE_MAX_LENGTH)
    private String title;

    @Column(length = BODY_MAX_LENGTH)
    private String body;
}
