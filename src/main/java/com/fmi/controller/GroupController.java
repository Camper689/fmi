package com.fmi.controller;

import com.fmi.domain.Group;
import com.fmi.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public String groupList(
            Model model
    ) {
        model.addAttribute("allGroups", groupService.getAllOrderByName());
        return "groups";
    }

    @PostMapping("/add")
    public String addGroupRequest(
            @RequestParam("course") Long course,
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ) {
        groupService.addRequest(course, name).write(redirectAttributes);
        return "redirect:/groups";
    }

    @PostMapping("/edit")
    public String editGroupRequest(
            @RequestParam("id") Long id,
            @RequestParam("course") Long course,
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ) {
        groupService.editRequest(id, course, name).write(redirectAttributes);
        return "redirect:/groups";
    }

    @PostMapping("/delete")
    public String delete(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        groupService.deleteRequest(id).write(redirectAttributes);
        return "redirect:/groups";
    }
}
