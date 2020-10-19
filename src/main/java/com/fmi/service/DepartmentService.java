package com.fmi.service;

import com.fmi.controller.RequestResult;
import com.fmi.controller.ResourceNotFoundException;
import com.fmi.domain.Department;
import com.fmi.domain.DepartmentPage;
import com.fmi.domain.DepartmentSection;
import com.fmi.domain.Teacher;
import com.fmi.repo.DepartmentPageRepo;
import com.fmi.repo.DepartmentRepo;
import com.fmi.repo.DepartmentSectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

import static com.fmi.controller.RequestResult.SUCCESS;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private DepartmentSectionRepo departmentSectionRepo;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private DepartmentPageRepo departmentPageRepo;

    public List<Department> getAllOrderById() {
        return departmentRepo.findAllByOrderById();
    }

    public void viewDepartmentRequest(Long departmentId, Long pageId, Model model) {
        Department department = departmentRepo.findOne(departmentId);
        if(department == null) throw new ResourceNotFoundException();

        model.addAttribute("department", department);
        model.addAttribute("teachers", teacherService.getAllByDepartment(department));
        model.addAttribute("sections", departmentSectionRepo.findAllByDepartmentFetchPagesOrderById(department.getId()));
        model.addAttribute("pages", pageId == null ?
                departmentPageRepo.findAllByDepartmentId(departmentId) :
                List.of(departmentPageRepo.findOne(pageId))
        );
    }

    public RequestResult addRequest(String name) {
        name = name.trim();
        if(name.isEmpty()) return RequestResult.ERROR_EMPTY;

        Department department = departmentRepo.findByNameIgnoreCase(name);
        if(department != null) return RequestResult.ERROR_EXISTS;

        department = new Department();
        department.setName(name);
        departmentRepo.save(department);

        return RequestResult.SUCCESS_ADDED;
    }

    public RequestResult editRequest(Long id, String name) {
        name = name.trim();
        if(name.isEmpty()) return RequestResult.ERROR_EMPTY;

        Department department = departmentRepo.findByNameIgnoreCase(name);
        if(department != null) return RequestResult.ERROR_EXISTS;

        department = departmentRepo.findOne(id);
        if(department == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        department.setName(name);
        departmentRepo.save(department);

        return RequestResult.SUCCESS_EDITED;
    }

    public RequestResult deleteRequest(Long id) {
        Department department = departmentRepo.findOne(id);
        if(department == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        if(department.hasChief()) {
            department.setChief(null);
            departmentRepo.save(department);
        }

        for (Teacher teacher : teacherService.getAllByDepartment(department)) {

        }

        return null;
    }


    public Department getOneById(Long id) {
        return departmentRepo.findOne(id);
    }

    public RequestResult setDeparmentChiefRequest(Long departmentId, Long teacherId) {
        Department department = departmentRepo.findOne(departmentId);
        Teacher teacher = teacherService.getOneByIdFetchDepartment(teacherId);
        if(department == null || teacher == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;
        if(!teacher.getDepartment().equals(department)) return RequestResult.ERROR_TEACHER_NOT_IN_DEPARTMENT;

        department.setChief(teacher);
        departmentRepo.save(department);
        return RequestResult.SUCCESS_EDITED;
    }

    public void save(Department department) {
        departmentRepo.save(department);
    }

    public RequestResult addSectionRequest(Long departmentId, String name) {
        name = name.trim();
        if(name.isEmpty()) return RequestResult.ERROR_EMPTY;

        Department department = departmentRepo.findOne(departmentId);
        if(department == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        String nameLower = name.toLowerCase();
        if (department.getSections().stream().anyMatch(
                departmentSection -> departmentSection.getName().toLowerCase().equals(nameLower)
        )) {
            return RequestResult.ERROR_EXISTS;
        }

        DepartmentSection section = new DepartmentSection();
        section.setName(name);
        section.setDepartment(department);
        departmentSectionRepo.save(section);

        return RequestResult.SUCCESS_ADDED;
    }

    public RequestResult editSectionRequest(Long id, String name) {
        name = name.trim();
        if(name.isEmpty()) return RequestResult.ERROR_EMPTY;

        DepartmentSection section = departmentSectionRepo.findOneFetchDepartment(id);
        if(section == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        String nameLower = name.toLowerCase();
        if (section.getDepartment().getSections().stream().anyMatch(
                departmentSection ->
                        departmentSection.getName().toLowerCase().equals(nameLower) &&
                        !departmentSection.getId().equals(id)
        )) {
            return RequestResult.ERROR_EXISTS;
        }

        section.setName(name);
        departmentSectionRepo.save(section);

        return RequestResult.SUCCESS_EDITED;
    }

    public RequestResult deleteSectionRequest(Long id) {
        DepartmentSection section = departmentSectionRepo.findOne(id);
        if(section == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        departmentSectionRepo.delete(section);
        return RequestResult.SUCCESS_DELETED;
    }

    public RequestResult editPageRequest(Long pageId, String title, String body, boolean fromChief) {
        title = title.trim(); body = body.trim();

        if(body.length() > DepartmentPage.BODY_MAX_LENGTH) return RequestResult.ERROR_BODY_LENGTH;
        if(title.length() > DepartmentPage.TITLE_MAX_LENGTH) return RequestResult.ERROR_TITLE_LENGTH;

        DepartmentPage page = departmentPageRepo.findOneFetchSection(pageId);
        if(page == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        // Якщо це пост для розділу
        if(page.getSection() != null) {
            // Назва поста обов'язкова
            if(title.isEmpty()) return RequestResult.ERROR_TITLE_REQUIRED_IF_IN_SECTION;

            // Якщо пост з такою назвою в цьому розділі вже існує, повертаємо помилку
            DepartmentPage exists = departmentPageRepo.findOneByTitleAndSectionId(title, page.getSection().getId());
            if(exists != null && !exists.getId().equals(pageId)) return RequestResult.ERROR_EXISTS;

            // Цей пост не від імені зав. кафедрою
            fromChief = false;
        } else {
            // Якщо пост для сторінки кафедри, цей пост від зав. кафедри, якщо назва поста не вказана
            fromChief = fromChief || title.isEmpty();
        }

        page.setTitle(title);
        page.setFromChief(fromChief);
        page.setBody(body);
        departmentPageRepo.save(page);

        return RequestResult.SUCCESS_EDITED;
    }

    public RequestResult deletePageRequest(Long id) {
        DepartmentPage page = departmentPageRepo.findOne(id);
        if(page == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        departmentPageRepo.delete(page);
        return RequestResult.SUCCESS_DELETED;
    }

    public DepartmentPage getPageById(Long id) {
        return departmentPageRepo.getOne(id);
    }

    public RequestResult updatePageRequest(
            Long id,
            Long departmentId,
            Long sectionId,
            String title,
            String body,
            boolean fromChief
    ) {
        title = title.trim(); body = body.trim();
        if(title.length() > DepartmentPage.TITLE_MAX_LENGTH) return RequestResult.ERROR_TITLE_LENGTH;
        if(body.length() > DepartmentPage.BODY_MAX_LENGTH) return RequestResult.ERROR_BODY_LENGTH;

        DepartmentPage page;

        // Якщо треба оновити існуючий пост, треба змінювати тільки заголовок і тіло сторінки
        if(id != null) {
            page = departmentPageRepo.findOne(id);
        }
        // Якщо треба додати нову сторінку
        else {
            // Має бути вказана або кафедра, або розділ
            if(departmentId == null && sectionId == null) return RequestResult.ERROR_EMPTY;

            page = new DepartmentPage();

            // Якщо це пост для розділу
            if(sectionId != null) {
                // Назва поста обов'язкова
                if(title.isEmpty()) return RequestResult.ERROR_TITLE_REQUIRED_IF_IN_SECTION;

                // Якщо пост з такою назвою в цьому розділі вже існує, повертаємо помилку
                DepartmentPage exists = departmentPageRepo.findOneByTitleAndSectionId(title, sectionId);
                if(exists != null) return RequestResult.ERROR_EXISTS;

                // Загружаємо розділ, якщо такого розділа нема повертаємо помилку
                DepartmentSection section = departmentSectionRepo.findOne(sectionId);
                if(section == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

                page.setSection(section);

                // Цей пост не від імені зав. кафедрою
                fromChief = false;
            } else {
                Department department = departmentRepo.findOne(departmentId);
                if(department == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

                page.setDepartment(department);

                fromChief = fromChief || title.isEmpty();
            }
        }

        page.setTitle(title);
        page.setFromChief(fromChief);
        page.setBody(body);
        departmentPageRepo.save(page);

        return SUCCESS;
    }

    public DepartmentSection getSectionById(Long sectionId) {
        return departmentSectionRepo.getOne(sectionId);
    }

    public DepartmentPage getPageByIdFetchDepartment(Long id) {
        return departmentPageRepo.findOneFetchDepartment(id);
    }
}