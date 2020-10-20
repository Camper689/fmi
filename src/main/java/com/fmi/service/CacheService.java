package com.fmi.service;

import com.fmi.domain.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class CacheService {

    @Autowired
    private PageService pageService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PostService postService;

    @Autowired
    private AlbumService albumService;

    private String navbar;

    private List<Section> sections = new ArrayList<>();

    private Section departmentSection;
    private Section newsSection;
    private Section gallerySection;

    @Getter
    @Setter
    private class Option {

        private long id = -1;

        private String label;

        private String url;

        private boolean visible = true;

        private Option() {}

        public Option(Section section) {
            section.addOption(this);
        }

        public String getHtml() {
            return String.format(
                    "<div class=\"col-sm-4\"><a class=\"btn btn-folder\" href=\"%s\">%s</a></div>",
                    this.url == null ? "#" : this.url,
                    this.label
            );
        }
    }

    @Getter
    @Setter
    private class Section {

        private String label;

        private String url;

        private String className;

        private List<Option> options = new ArrayList<>();

        public void addOption(Option option) {
            this.options.add(option);
        }

        public Section(String label, String url, String className) {
            this.label = label;
            this.url = url;
            this.className = className;
            sections.add(this);
        }

        public String getHtml() {
            String html = String.format(
                    "<li><a class=\"main-item\" href=\"%s\"><i class=\"%s\"></i> %s</a>",
                    this.url,
                    this.className == null ? "" : this.className,
                    this.label
            );

            if(!options.isEmpty()) {
                html = html + "<ul class=\"menu-dropdown-content\"><div class=\"row\">";
                for (Option option : options) {
                    html = html + option.getHtml();
                }
            }

            return html + "</li>";
        }
    }

    public String getNavbar() {
        return navbar;
    }

    @PostConstruct
    public void loadData() {
        // Завантажуємо секції зі сторінками
        for (PageSection pageSection : pageService.getAllSectionsFetchMainPageOrderById()) {
            Section section = new Section(
                    pageSection.getName(),
                    pageSection.getMainPage() == null ? null : pageSection.getMainPage().getUrl(),
                    pageSection.getClassName()
            );

            sections.add(section);

            for (Page page : pageService.getAllBySectionOrderById(pageSection)) {
                Option option = new Option(section);
                option.setId(page.getId());
                option.setUrl("/pages/" + page.getUrl());
                option.setVisible(page.isVisible());
                option.setLabel(page.getName());
            }
        }

        // Підгружаємо кафедри
        departmentSection = new Section("Кафедри", "/departments", "fa fa-users");
        for (Department department : departmentService.getAllOrderById()) {
            Option option = new Option(departmentSection);

            option.setId(department.getId());
            option.setLabel(department.getName());
            option.setId(department.getId());
            option.setUrl("/departments/" + department.getId());
        }

        // Пости
        newsSection = new Section("Новини", "/posts", "fa fa-newspaper-o");
        Option allNewsOption = new Option(newsSection);
        allNewsOption.setLabel("Всі новини");
        allNewsOption.setUrl("/posts");

        for (Post post : postService.getSomeLastPosts(7)) {
            Option option = new Option(newsSection);
            option.setLabel(post.getTitle());
            option.setUrl("/posts/" + post.getId());
            option.setId(post.getId());
        }

        // І галереї
        gallerySection = new Section("Галерея", "/gallery", "fa fa-picture-o");

        for (Album album : albumService.getAllOrderByIdDesc()) {
            Option option = new Option(gallerySection);
            option.setUrl("/gallery/" + album.getId());
            option.setLabel(album.getName());
            option.setId(album.getId());
        }

        updateNavbar();
    }

    private void updateNavbar() {
        String newNavbar = "<ul>";
        for (Section section : sections) {
            newNavbar += section.getHtml();
        }
        this.navbar = newNavbar + "</ul>";
    }
}
