package com.fmi.controller;

import com.fmi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/teacherStatus")
public class TeacherStatusController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public String teacherStatusList(
            Model model
    ) {
        model.addAttribute("allTeacherStatuses", teacherService.getAllTeacherStatuses());
        return "teacherStatuses";
    }

    @PostMapping("/add")
    public String teacherStatusAdd(
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ) {
        teacherService.addTeacherStatusRequest(name).write(redirectAttributes);
        return "redirect:/teacherStatus";
    }

    @PostMapping("/edit")
    public String teacherStatusEdit(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ) {
        teacherService.editTeacherStatusRequest(id, name).write(redirectAttributes);
        return "redirect:/teacherStatus";
    }

    @PostMapping("/delete")
    public String teacherStatusDelete(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        teacherService.deleteTeacherStatusRequest(id).write(redirectAttributes);
        return "redirect:/teacherStatus";
    }
}
