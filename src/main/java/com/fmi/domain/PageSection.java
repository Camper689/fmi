package com.fmi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Сутність, яка представляє собою секцію сторінок
// Наприклад Освітня діяльність, Про факультет або Абітурієнту

@Entity
@Data
public class PageSection {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // Клас font-awesome для відображення на нав. панелі. Можна залишити пустим
    private String className;

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    private List<Page> pages = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private Page mainPage;

    @Override
    public String toString() {
        return "PageSection{" +
                "id=" + id +
                '}';
    }
}
