package com.fmi.service;

import com.fmi.controller.RequestResult;
import com.fmi.controller.ResourceNotFoundException;
import com.fmi.domain.Page;
import com.fmi.domain.PageSection;
import com.fmi.repo.PageRepo;
import com.fmi.repo.PageSectionRepo;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;

import static com.fmi.controller.RequestResult.*;

@Service
public class PageService {

    @Autowired
    private PageRepo pageRepo;

    @Autowired
    private PageSectionRepo pageSectionRepo;

    public void viewEditSectionPageRequest(Model model) {
        Set<PageSection> allSections = pageSectionRepo.findAllFetchPagesAndMainPage();
        model.addAttribute("allSections", allSections);
    }

    public void viewEditPageRequest(Long id, Model model) {
        Page page = pageRepo.findOneFetchSubPages(id);
        if(page == null) throw new ResourceNotFoundException();

        model.addAttribute("page", page);
    }

    public Long addSectionRequest(String name, String className) {
        PageSection section = new PageSection();
        section.setName(name);
        section.setClassName(className);
        pageSectionRepo.save(section);

        return section.getId();
    }

    public RequestResult editSectionRequest(Long id, String name, String className) {
        PageSection section = pageSectionRepo.findOne(id);
        if(section == null) return ERROR_NOT_FOUND_BY_ID;
        section.setName(name);
        section.setClassName(className);
        pageSectionRepo.save(section);

        return SUCCESS_EDITED;
    }

    public RequestResult deleteSectionRequest(Long id) {
        PageSection section = pageSectionRepo.findOne(id);
        if(section == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        pageSectionRepo.delete(section);
        return RequestResult.SUCCESS_DELETED;
    }

    public RequestResult setSectionMainPageRequest(Long sectionId, Long pageId) {
        PageSection section = pageSectionRepo.findOne(sectionId);
        if(section == null) return ERROR_NOT_FOUND_BY_ID;

        Page page = pageRepo.findOneFetchSection(pageId);
        if(page == null) return ERROR_NOT_FOUND_BY_ID;
        if(page.getSection() == null || !section.getId().equals(page.getSection().getId()))
            return RequestResult.ERROR_PAGE_IS_NOT_IN_SECTION;

        section.setMainPage(page);
        pageSectionRepo.save(section);

        return SUCCESS_EDITED;
    }

    private String getNewUrl(Long prefix, String name, Long exceptId) {
        // Посилання отримуємо із транслітерації назви сторінки
        String url = TransliteratorUtils.transliterate(name);
        // Якщо сторінка з таким посиланням вже існує
        Page oneByUrl = pageRepo.findOneByUrl(url);
        // Якщо таке посилання існує і це не стаття, яку ми редагуємо
        if(oneByUrl != null && !oneByUrl.getId().equals(exceptId)) {
            // Генеруємо довшу назву сторінки, буде починатись з префіксу + __. Якщо така сторінка теж існує, збільшуємо кількість __
            String space = "__";
            String newUrl;
            do {
                // Генеруємо нове посилання
                newUrl = prefix + space + url;
                space = space + "_";
                // Шукаємо статтю з таким посиланням
                oneByUrl = pageRepo.findOneByUrl(newUrl);
                // Якщо сторінки з таким посиланням нема або це поточна сторінка, можна повертати таке посилання
            } while (oneByUrl != null && !oneByUrl.getId().equals(exceptId));
            url = newUrl;
        }

        return url;
    }

    public Pair<RequestResult, Page> addPageToPageRequest(Long pageId, String name) {
        name = name.trim();
        if(name.isEmpty()) return Pair.of(ERROR_EMPTY, new Page());

        Page parentPage = pageRepo.findOne(pageId);
        if(parentPage == null) return Pair.of(ERROR_NOT_FOUND_BY_ID, new Page());

        Page page = new Page();
        page.setParentPage(parentPage);
        page.setName(name);

        String url = getNewUrl(parentPage.getId(), name, -1L);

        page.setUrl(url);
        pageRepo.save(page);

        return Pair.of(SUCCESS_ADDED, page);
    }

    public RequestResult editPageRequest(Long id, String name, String body) {
        name = name.trim(); body = body.trim();
        if(name.isEmpty()) return ERROR_EMPTY;

        Page page = pageRepo.findOne(id);
        if(page == null) return ERROR_NOT_FOUND_BY_ID;

        page.setName(name);
        page.setBody(body);

        Long prefixId = page.getParentId();
        String url = getNewUrl(prefixId, name, page.getId());

        page.setUrl(url);
        pageRepo.save(page);

        return SUCCESS_EDITED;
    }

    // Обробка запиту на додавання сторінки в корінь секції
    public Pair<RequestResult, Page> addPageToSectionRequest(Long sectionId, String name) {
        name = name.trim();
        if(name.isEmpty()) return Pair.of(ERROR_EMPTY, new Page());

        PageSection section = pageSectionRepo.findOne(sectionId);
        if(section == null) return Pair.of(ERROR_NOT_FOUND_BY_ID, new Page());

        Page page = new Page();
        page.setSection(section);
        page.setName(name);

        String url = getNewUrl(sectionId, name, -1L);
        page.setUrl(url);

        pageRepo.save(page);

        return Pair.of(SUCCESS_ADDED, page);
    }

    public void setPageVisible(Page page) {
        page.setVisible(!page.isVisible());
        pageRepo.save(page);
    }

    public void viewPageRequest(String url, Model model) {
        Page oneByUrl = pageRepo.findOneByUrl(url);
        if(oneByUrl == null) throw new ResourceNotFoundException();

        model.addAttribute("page", oneByUrl);
    }

    public List<PageSection> getAllSectionsFetchMainPageOrderById() {
        return pageSectionRepo.findAllSectionsFetchMainPageOrderById();
    }

    public List<Page> getAllBySectionOrderById(PageSection pageSection) {
        return pageRepo.findAllBySectionId(pageSection.getId());
    }
}
