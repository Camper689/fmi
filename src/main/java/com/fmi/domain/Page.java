package com.fmi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Page {

    @Id
    @GeneratedValue
    private Long id;

    // Якщо це поле не null, ця сторінка буде в корені цієї секції, інакше треба щоб page було не null
    @ManyToOne(fetch = FetchType.EAGER)
    private PageSection section;

    @ManyToOne(fetch = FetchType.EAGER)
    private Page parentPage;

    @OneToMany(mappedBy = "parentPage", fetch = FetchType.LAZY)
    private List<Page> subPages = new ArrayList<>();

    @Column(length = 300)
    private String name;

    @Column(length = 500)
    private String url;

    @Column(length = 10000)
    private String body = "";

    private boolean visible = false;

    public boolean isInRoot() {
        return section != null;
    }

    public boolean hasSubPages() {
        return !subPages.isEmpty();
    }

    public Long getParentId() {
        return section == null ? parentPage.getId() : section.getId();
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(id, page.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
