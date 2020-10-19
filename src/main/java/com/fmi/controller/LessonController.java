package com.fmi.controller;

import com.fmi.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @GetMapping
    public String groupList(
            Model model
    ) {
        model.addAttribute("allLessons", lessonService.getAllOrderByName());
        return "lessons";
    }

    @PostMapping("/add")
    public String addGroupRequest(
            @RequestParam("name") String name,
            @RequestParam("shortName") String shortName,
            RedirectAttributes redirectAttributes
    ) {
        lessonService.addRequest(name, shortName).write(redirectAttributes);
        return "redirect:/lessons";
    }

    @PostMapping("/edit")
    public String editGroupRequest(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("shortName") String shortName,
            RedirectAttributes redirectAttributes
    ) {
        lessonService.editRequest(id, name, shortName).write(redirectAttributes);
        return "redirect:/lessons";
    }

    @PostMapping("/delete")
    public String delete(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        lessonService.deleteRequest(id).write(redirectAttributes);
        return "redirect:/lessons";
    }
}
