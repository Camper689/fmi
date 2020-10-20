package com.fmi.domain;

import lombok.Data;
import org.springframework.web.util.HtmlUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Category category;

    @OneToOne(fetch = FetchType.EAGER)
    private Image image;

    @Column(length = 300)
    private String title;

    @Column(length = 10000)
    private String body;

    private LocalDate date;

    private boolean visible = false;

    @OneToOne(fetch = FetchType.LAZY)
    private Album album;

    public String getPreview() {
        if(body == null) return "<пусто>";
        String noTags = body.replaceAll("<.+?>", " ").replaceAll(" {2,}", " ");
        System.out.println("NOTAGS = " + noTags);
        return noTags.length() > 140 ? noTags.substring(0, 140) + "..." : noTags;
    }

    public String getFormatDate() {
        return date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.UK));
    }
}
