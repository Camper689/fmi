package com.fmi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Page {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Page page;

    @OneToMany(mappedBy = "page")
    private List<Page> subPages = new ArrayList<>();
}
