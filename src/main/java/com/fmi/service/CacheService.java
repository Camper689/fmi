package com.fmi.service;

import com.fmi.domain.*;
import freemarker.template.TemplateModelException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import java.util.*;

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

    @Autowired
    private FreeMarkerConfigurer configurer;

    private Navbar navbar;
    private Map<String, Sidebar> sidebars = new HashMap<>();

    public void albumAdded(Album album) {
        navbar.albumAdded(album);
    }

    public void albumEdited(Album album) {
        navbar.albumEdited(album);
    }

    public void albumDeleted(Long id) {
        navbar.albumDeleted(id);
    }

    public void postAdded(Post post) {
        navbar.postAdded(post);
    }

    // Клас що відповідає за оновлення навігаційної панелі
    // Містить інформацію про всі кнопки на цій панелі
    public class Navbar {
        private List<Section> sections = new ArrayList<>();
        private Section departmentSection;
        private Section newsSection;
        private Section gallerySection;
        private String html;

        public Navbar() {
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
            updateNews();

            // І галереї
            gallerySection = new Section("Галерея", "/gallery", "fa fa-picture-o");
            updateGallery();

            sections.add(departmentSection);
            sections.add(newsSection);
            sections.add(gallerySection);

            updateHtml();
        }

        private void updateHtml() {
            String newHtml = "<ul>";
            for (Section section : sections) {
                newHtml += section.getHtml(section == newsSection);
            }
            this.html = newHtml + "</ul>";

            System.out.println("Новий навбар: " + html);
        }

        public void albumAdded(Album album) {
            if (gallerySection.size() == 12) gallerySection.removeLast();
            Option option = new Option();
            option.setId(album.getId());
            option.setLabel(album.getName());
            option.setUrl("/gallery/" + album.getId());
            gallerySection.addFirst(option);

            updateHtml();
        }

        public void albumEdited(Album album) {
            gallerySection.update(album);
        }

        public void albumDeleted(long id) {
            if(gallerySection.getOptionById(id) != null) updateGallery();
        }

        private void updateGallery() {
            gallerySection.clear();
            int p = 0;
            for (Album album : albumService.getAllOrderByIdDesc()) {
                Option option = new Option(gallerySection);
                option.setUrl("/gallery/" + album.getId());
                option.setLabel(album.getName());
                option.setId(album.getId());
                if(++p == 12) return;
            }
        }

        private void updateNews() {
            newsSection.clear();
            Option allNewsOption = new Option(newsSection);
            allNewsOption.setLabel("Всі новини");
            allNewsOption.setUrl("/posts");

            for (Post post : postService.getAllOrderByIdDesc()) {
                Option option = new Option(newsSection);
                option.setLabel(post.getTitle());
                option.setUrl("/posts/" + post.getId());
                option.setVisible(post.isVisible());
                option.setId(post.getId());
            }
        }

        public void postAdded(Post post) {

        }

        @Getter
        @Setter
        private class Option {
            private long id = -1;
            private String label;
            private String url;
            private boolean visible = true;

            public Option() { }

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

            public void update(Album album) {
                label = album.getName();
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

            private Section() {
            }

            public Section(String label, String url, String className) {
                this.label = label;
                this.url = url;
                this.className = className;
                sections.add(this);
            }

            public String getHtml(boolean isNews) {
                String html = String.format(
                        "<li><a class=\"main-item\" href=\"%s\"><i class=\"%s\"></i> %s</a>",
                        this.url,
                        this.className == null ? "" : this.className,
                        this.label
                );

                if (!options.isEmpty()) {
                    html = html + "<ul class=\"menu-dropdown-content\"><div class=\"row\">";
                    int p = 0;
                    for (Option option : options) {
                        if(!option.isVisible()) continue;
                        html = html + option.getHtml();
                        // Для новин відображає тільки 12 записів
                        if(isNews && ++p == 12) break;
                    }
                    html = html + "</ul>";
                }

                return html + "</li>";
            }

            public long size() {
                return options.size();
            }

            public void removeLast() {
            }

            public void addFirst(Option option) {
                options.add(0, option);
            }

            public Option getOptionById(Long id) {
                for (Option option : options) {
                    if(id.equals(option.getId())) return option;
                }

                return null;
            }

            public boolean update(Album album) {
                Option option = getOptionById(album.getId());
                if(option != null) option.update(album);
                return option != null;
            }

            public void clear() {
                options.clear();
            }

            public int visibleSize() {
                int r = 0;
                for (Option option : options) if (option.isVisible()) r++;

                return r;
            }
        }
    }

    @Getter
    @Setter
    public class Sidebar {
        private Set<SidebarOption> options = new TreeSet<>();
        private String html;

        private void updateHtml() {
            StringBuilder builder = new StringBuilder().append("<ul class=\"page-menu\">");
            for(var option : options)
                option.loadHtml(builder, 1);
            html = builder.append("</ul>").toString();
        }

        private Sidebar() {};

        public Sidebar(PageSection section) {
            if(section.getMainPage() != null) {
                SidebarOption option = new SidebarOption(
                        section.getMainPage().getId(),
                        section.getMainPage().getName(),
                        section.getMainPage().getUrl(),
                        true,
                        section.getMainPage().isVisible()
                );
                options.add(option);
            }

            for (Page page : pageService.getAllBySectionOrderById(section)) {
                SidebarOption option = new SidebarOption(page.getId(), page.getName(), page.getUrl(), false, page.isVisible());
                options.add(option);
            }

            updateHtml();
        }

        @Getter
        @Setter
        private class SidebarOption implements Comparable<SidebarOption> {
            private long id;
            private String label;
            private String url;
            private Set<SidebarOption> options = new TreeSet<>(Comparator.comparing(SidebarOption::getId));
            private boolean isMain = false;
            private boolean isVisible = true;

            public SidebarOption(long id, String label, String url, boolean isMain, boolean isVisible) {
                this.id = id;
                this.label = label;
                this.url = url;
                this.isMain = isMain;
                this.isVisible = isVisible;
            }

            private StringBuilder loadHtml(StringBuilder builder, int level) {
                if(!isVisible) return builder;
                builder.append("<li><a style=\"padding-left: ")
                        .append(level * 12)
                        .append("px\" href=\"/pages/")
                        .append(this.url)
                        .append("\">")
                        .append(this.label)
                        .append("</a></li>\n");

                for (SidebarOption option : options)
                    if (option.isVisible()) loadHtml(builder, level + 1);

                return builder;
            }

            @Override
            public int compareTo(SidebarOption o) {
                if (o.isMain != isMain) return isMain ? 1 : -1;
                return (int) (id - o.id);
            }
        }
    }

    @PostConstruct
    public void loadData() {
        navbar = new Navbar();
        for (PageSection pageSection : pageService.getAllSectionsFetchMainPageOrderById()) {
            Sidebar sidebar = new Sidebar(pageSection);
            sidebars.put(String.valueOf(pageSection.getId()), sidebar);
        }

        try {
            configurer.getConfiguration().setSharedVariable("navbar", navbar);
            configurer.getConfiguration().setSharedVariable("sidebars", sidebars);
        } catch (TemplateModelException e) {
            System.out.println("Помилка оновлення navbar: ");
            e.printStackTrace();
        }

    }
}
