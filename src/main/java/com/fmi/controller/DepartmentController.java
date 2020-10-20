package com.fmi.controller;

import com.fmi.domain.*;
import com.fmi.service.DepartmentService;
import com.fmi.service.GroupService;
import com.fmi.service.TeacherService;
import com.fmi.service.TimetableService;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private TimetableService timetableService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private GroupService groupService;

    @GetMapping("/departments")
    public String departmentList(
            Model model
    ) {
        model.addAttribute("allDepartments", departmentService.getAllOrderById());
        return "departments";
    }

    @GetMapping("/departments/{id}/teachers")
    public String teacherList(
            @PathVariable("id") Long id,
            Model model
    ) {
        teacherService.viewDepartmentTeachersRequest(id, model);
        return "teachers";
    }

    @GetMapping("/timetables")
    public String timetables(
            Model model
    ) {
        model.addAttribute("allTeachers", teacherService.getAllOrderByFirstName());
        model.addAttribute("allGroups", groupService.getAllOrderByName());

        return "timetables";
    }

    @GetMapping("/groups/{group}")
    public String groupTimetable(
            @PathVariable("group") Group group,
            Model model
    ) {
        model.addAttribute("timetableGrid", timetableService.getTimetablesForGroup(group));
        return "group";
    }

    @GetMapping("/departments/{department}")
    public String viewDepartment(
            @PathVariable("department") Long id,
            Model model
    ) {
        departmentService.viewDepartmentRequest(id, null, model);
        return "department";
    }

    @GetMapping("/departments/{department}/pages/{page}")
    public String viewSectionPage(
            @PathVariable("department") Long department,
            @PathVariable("page") Long page,
            Model model
    ) {
        departmentService.viewDepartmentRequest(department, page, model);
        return "department";
    }

    @GetMapping("/teacher/{id}")
    public String viewTeacher(
            @PathVariable("id") Teacher teacher,
            Model model
    ) {
        teacherService.viewTeacherRequest(teacher.getId(), model);
        model.addAttribute("timetableGrid", timetableService.getTimetablesForTeacher(teacher));

        return "teacher";
    }

    @PostMapping("/timetables/set")
    @ResponseBody
    public String setTimetable(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam("group") Group group,
            @RequestParam("lesson") Lesson lesson,
            @RequestParam("teacher") Teacher teacher,
            @RequestParam("oneWeekOnly") boolean oneWeekOnly,
            RedirectAttributes redirectAttributes
    ) {
        RequestResult requestResult = timetableService.setRequest(id, group, lesson, teacher, oneWeekOnly);
        return requestResult.isSuccess() ? "OK" : requestResult.getMessage();
    }

    @PostMapping("/timetables/delete")
    @ResponseBody
    public String deleteTimetable(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        RequestResult requestResult = timetableService.deleteRequest(id);
        return requestResult.isSuccess() ? "OK" : requestResult.getMessage();
    }

    @PostMapping("/departments/addTeacher")
    public String addTeacher(
            @RequestParam("department") Department department,
            @RequestParam("firstName") String firstName,
            @RequestParam("middleName") String middleName,
            @RequestParam("lastName") String lastName,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "status", required = false) TeacherStatus status,
            RedirectAttributes redirectAttributes
    ) {
        teacherService.addRequest(department, firstName, middleName, lastName, avatar, status).write(redirectAttributes);
        return "redirect:/departments/" + department.getId() + "/teachers";
    }

    @PostMapping("/departments/editTeacher")
    public String editTeacher(
            @RequestParam("departmentId") Long departmentId,
            @RequestParam("id") Long teacher,
            @RequestParam("firstName") String firstName,
            @RequestParam("middleName") String middleName,
            @RequestParam("lastName") String lastName,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar,
            @RequestParam(value = "status", required = false) TeacherStatus status,
            RedirectAttributes redirectAttributes
    ) {
        teacherService.editRequest(teacher, firstName, middleName, lastName, avatar, status).write(redirectAttributes);
        return "redirect:/departments/" + departmentId + "/teachers";
    }

    @PostMapping("/teacher/setInfo")
    public String setTeacherInfo(
            @RequestParam("id") Long id,
            @RequestParam("info") String info,
            RedirectAttributes redirectAttributes
    ) {
        teacherService.setTeacherInfoRequest(id, info).write(redirectAttributes);
        return "redirect:/teacher/" + id;
    }

    @PostMapping("/departments/deleteTeacher")
    public String deleteTeacher(
            @RequestParam("departmentId") Long departmentId,
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        teacherService.deleteRequest(id).write(redirectAttributes);
        return "redirect:/departments/" + departmentId;
    }

    @PostMapping("/departments/setChief")
    public String setDepartmentChief(
            @RequestParam("department") Long department,
            @RequestParam("teacher") Long teacher,
            RedirectAttributes redirectAttributes
    ) {
        departmentService.setDeparmentChiefRequest(department, teacher).write(redirectAttributes);
        return "redirect:/departments/" + department + "/teachers";
    }

    @PostMapping("/departments/add")
    public String addDepartment(
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ) {
        departmentService.addRequest(name).write(redirectAttributes);
        return "redirect:/departments";
    }

    @PostMapping("/departments/edit")
    public String editDepartment(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ) {
        departmentService.editRequest(id, name).write(redirectAttributes);
        return "redirect:/departments";
    }

    @PostMapping("/departments/delete")
    public String deleteDepartment(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        departmentService.deleteRequest(id).write(redirectAttributes);
        return "redirect:/departments";
    }

    @PostMapping("/departments/addSection")
    public String addSection(
            @RequestParam("department") Long department,
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ) {
        departmentService.addSectionRequest(department, name).write(redirectAttributes);
        return "redirect:/departments/" + department;
    }

    @PostMapping("/departments/editSection")
    public String editSection(
            @RequestParam("id") Long id,
            @RequestParam("department") Long departmentId,
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ) {
        departmentService.editSectionRequest(id, name).write(redirectAttributes);
        return "redirect:/departments/" + departmentId;
    }

    @PostMapping("/departments/deleteSection")
    public String deleteSection(
            @RequestParam("id") Long id,
            @RequestParam("departmentId") Long departmentId,
            RedirectAttributes redirectAttributes
    ) {
        departmentService.deleteSectionRequest(id).write(redirectAttributes);
        return "redirect:/departments/" + departmentId;
    }

    @GetMapping("/departments/page")
    public String post(
            @RequestParam(value = "page", required = false) Long id,
            @RequestParam(value = "department", required = false) Long departmentId,
            @RequestParam(value = "section", required = false) Long sectionId,
            Model model
    ) {
        if(id == null && departmentId == null && sectionId == null) return "redirect:/departments";

        if(id != null) model.addAttribute("page", departmentService.getPageByIdFetchDepartment(id));
        if(departmentId != null) model.addAttribute("department", departmentService.getOneById(departmentId));
        if(sectionId != null) model.addAttribute("section", departmentService.getSectionById(sectionId));

        return "departmentPageEditor";
    }

    @PostMapping("/departments/updatePage")
    public String updatePage(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "departmentId", required = false) Long departmentId,
            @RequestParam(value = "sectionId", required = false) Long sectionId,
            @RequestParam(value = "fromChief", required = false, defaultValue = "false") Boolean fromChief,
            @RequestParam("title") String title,
            @RequestParam("body") String body,
            RedirectAttributes redirectAttributes
    ) {
        RequestResult result = departmentService.updatePageRequest(id, departmentId, sectionId, title, body, fromChief).write(redirectAttributes);
        if(!result.isSuccess()) {
            redirectAttributes.addFlashAttribute("fromChief", fromChief);
            redirectAttributes.addFlashAttribute("title", title);
            redirectAttributes.addFlashAttribute("body", body);
        }

        return id == null ?
                departmentId == null ?
                        "redirect:/departments/page?section=" + sectionId :
                        "redirect:/departments/page?department=" + departmentId :
                "redirect:/departments/page?page=" + id;
    }
}
