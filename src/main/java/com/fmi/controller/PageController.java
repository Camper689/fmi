package com.fmi.controller;

import com.fmi.domain.Page;
import com.fmi.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PageController {

    @Autowired
    private PageService pageService;

    // Сторінка для редагування секцій
    @GetMapping("/sections")
    public String sectionList(
            Model model
    ) {
        pageService.viewEditSectionPageRequest(model);
        return "sections";
    }

    @GetMapping("/pages/{url}")
    public String viewPage(
            @PathVariable("url") String url,
            Model model
    ) {
        pageService.viewPageRequest(url, model);
        return "page";
    }

    // Сторінка для редагування конкретної сторінки
    @GetMapping("/page/{id}")
    public String editPage(
            @PathVariable("id") Long id,
            Model model
    ) {
        pageService.viewEditPageRequest(id, model);
        return "pageEditor";
    }

    @PostMapping("/page/setVisible")
    public String setPageVisible(
            @RequestParam("id") Page page
    ) {
        pageService.setPageVisible(page);
        return "redirect:/page/" + page.getId();
    }

    @PostMapping("/page/edit")
    public String editPage(
            @RequestParam("id") Long pageId,
            @RequestParam("name") String name,
            @RequestParam("body") String body,
            RedirectAttributes redirectAttributes
    ) {
        pageService.editPageRequest(pageId, name, body).write(redirectAttributes);
        return "redirect:/page/" + pageId;
    }

    // Додавання сторінки в корінь секції
    @PostMapping("/section/addPage")
    public String addSectionPage(
            @RequestParam("id") Long sectionId,
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ) {
        Pair<RequestResult, Page> result = pageService.addPageToSectionRequest(sectionId, name);
        // Якщо сторінка була створена, перенаправить на редагування сторінки, інакше напише помилку і поверне до списку секцій
        return "redirect:/" + (result.getFirst().write(redirectAttributes).isSuccess() ?
                "page/" + result.getSecond().getId() :
                "sections/"
        );
    }

    // Додавання дочірньої сторінки
    @PostMapping("/page/addSubPage")
    public String addSubPage(
            @RequestParam("id") Long pageId,
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ) {
        Pair<RequestResult, Page> result = pageService.addPageToPageRequest(pageId, name);
        // Якщо сторінка була створена, перенаправить на редагування сторінки, інакше напише помилку і поверне до списку секцій
        return "redirect:/" + (result.getFirst().write(redirectAttributes).isSuccess() ?
                "page/" + result.getSecond().getId() :
                "page/" + pageId
        );
    }

    // Змінити головну сторінку секції
    @PostMapping("/section/setMainPage")
    public String setMainPage(
            @RequestParam("id") Long sectionId,
            @RequestParam("page") Long pageId,
            RedirectAttributes redirectAttributes
    ) {
        pageService.setSectionMainPageRequest(sectionId, pageId).write(redirectAttributes);
        return "redirect:/sections";
    }

    // Додавання секції
    @PostMapping("/section/add")
    public String addSection(
            @RequestParam("name") String name,
            @RequestParam("className") String className
    ) {
        pageService.addSectionRequest(name, className);
        return "redirect:/sections";
    }

    // Редагування секції
    @PostMapping("/section/edit")
    public String editSection(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("className") String className,
            RedirectAttributes redirectAttributes
    ) {
        pageService.editSectionRequest(id, name, className);
        return "redirect:/sections";
    }

    @PostMapping("/section/delete")
    public String delete(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        pageService.deleteSectionRequest(id).write(redirectAttributes);
        return "redirect:/";
    }
}
