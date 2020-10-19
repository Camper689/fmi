package com.fmi.service;

import com.fmi.controller.RequestResult;
import com.fmi.controller.ResourceNotFoundException;
import com.fmi.domain.Department;
import com.fmi.domain.Image;
import com.fmi.domain.Teacher;
import com.fmi.domain.TeacherStatus;
import com.fmi.repo.TeacherRepo;
import com.fmi.repo.TeacherStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private TeacherStatusRepo teacherStatusRepo;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ImageService imageService;

    public RequestResult addRequest(Department department, String firstName, String middleName, String lastName, MultipartFile avatar, TeacherStatus status) {
        firstName = firstName.trim(); middleName = middleName.trim(); lastName = lastName.trim();
        if(department == null || firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty()) return RequestResult.ERROR_EMPTY;

        Teacher teacher = teacherRepo.findByNameIgnoreCase(firstName, middleName, lastName);
        if(teacher != null) return RequestResult.ERROR_EXISTS;

        teacher = new Teacher();

        teacher.setFirstName(firstName); teacher.setMiddleName(middleName); teacher.setLastName(lastName);
        teacher.setDepartment(department);

        if(status != null) teacher.setStatus(status);
        if(avatar != null && !avatar.isEmpty()) {
            Pair<ImageUploadResult, Image> imageUploadResultImagePair = imageService.saveAvatar(avatar);
            if(imageUploadResultImagePair.getFirst() == ImageUploadResult.SUCCESS) {
                imageService.save(imageUploadResultImagePair.getSecond());
                teacher.setAvatar(imageUploadResultImagePair.getSecond());
            } else {
                teacherRepo.save(teacher);
                return RequestResult.SUCCESS_AVATAR_ERROR;
            }
        }

        teacherRepo.save(teacher);
        return RequestResult.SUCCESS_ADDED;
    }

    public RequestResult editRequest(
            Long id,
            String firstName,
            String middleName,
            String lastName,
            MultipartFile avatar,
            TeacherStatus status
    ) {
        firstName = firstName.trim(); middleName = middleName.trim(); lastName = lastName.trim();
        if(firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty()) return RequestResult.ERROR_EMPTY;

        Teacher teacher = teacherRepo.findByNameIgnoreCase(firstName, middleName, lastName);
        if(teacher != null && !teacher.getId().equals(id)) return RequestResult.ERROR_EXISTS;

        teacher = teacherRepo.findOne(id);
        if(teacher == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        teacher.setFirstName(firstName); teacher.setMiddleName(middleName); teacher.setLastName(lastName);

        teacher.setStatus(status);
        if(avatar != null && !avatar.isEmpty()) {
            System.out.println("Міняємо аватар викладача");
            Pair<ImageUploadResult, Image> imageUploadResultImagePair = imageService.saveAvatar(avatar);
            if(imageUploadResultImagePair.getFirst() == ImageUploadResult.SUCCESS) {
                if(teacher.getAvatar() != null) imageService.delete(teacher.getAvatar());
                imageService.save(imageUploadResultImagePair.getSecond());
                teacher.setAvatar(imageUploadResultImagePair.getSecond());
            } else {
                teacherRepo.save(teacher);
                return RequestResult.SUCCESS_AVATAR_ERROR;
            }
        }

        teacherRepo.save(teacher);
        return RequestResult.SUCCESS_EDITED;
    }

    public RequestResult deleteRequest(Long id) {
        Teacher teacher = teacherRepo.findOneFetchDepartment(id);
        if(teacher == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        if(teacher.equals(teacher.getDepartment().getChief())) {
            teacher.getDepartment().setChief(null);
            departmentService.save(teacher.getDepartment());
        }

        if(teacher.getAvatar() == null) imageService.delete(teacher.getAvatar());

        teacherRepo.delete(teacher);
        return RequestResult.SUCCESS_DELETED;
    }

    public List<TeacherStatus> getAllTeacherStatuses() {
        return teacherStatusRepo.findAll();
    }

    public void viewDepartmentTeachersRequest(Long id, Model model) {
        Department department = departmentService.getOneById(id);
        if(department == null) throw new ResourceNotFoundException();

        List<Teacher> allTeachers = teacherRepo.findAllByDepartmentOrderByFirstName(department);

        model.addAttribute("department", department);
        model.addAttribute("allTeachers", allTeachers);
        model.addAttribute("allTeacherStatuses", getAllTeacherStatuses());
    }

    public Teacher getOneByIdFetchDepartment(Long teacherId) {
        return teacherRepo.findOneByIdFetchDepartment(teacherId);
    }

    public RequestResult addTeacherStatusRequest(String name) {
        name = name.trim();
        if(name.isEmpty()) return RequestResult.ERROR_EMPTY;

        TeacherStatus teacherStatus = teacherStatusRepo.findByNameIgnoreCase(name);
        if(teacherStatus != null) return RequestResult.ERROR_EXISTS;

        teacherStatus = new TeacherStatus();
        teacherStatus.setName(name);
        teacherStatusRepo.save(teacherStatus);
        return RequestResult.SUCCESS_ADDED;
    }

    public RequestResult editTeacherStatusRequest(Long id, String name) {
        name = name.trim();
        if(name.isEmpty()) return RequestResult.ERROR_EMPTY;

        TeacherStatus teacherStatus = teacherStatusRepo.findByNameIgnoreCase(name);
        if(teacherStatus != null) return RequestResult.ERROR_EXISTS;

        teacherStatus = teacherStatusRepo.findOne(id);
        if(teacherStatus == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        teacherStatus.setName(name);
        teacherStatusRepo.save(teacherStatus);
        return RequestResult.SUCCESS_EDITED;
    }

    public RequestResult deleteTeacherStatusRequest(Long id) {
        TeacherStatus status = teacherStatusRepo.findOne(id);
        if(status == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        for (Teacher teacher : teacherRepo.findAllByStatus(status)) {
            teacher.setStatus(null);
            teacherRepo.save(teacher);
        }

        teacherStatusRepo.delete(status);
        return RequestResult.SUCCESS_DELETED;
    }

    public List<Teacher> getAllByDepartment(Department department) {
        return teacherRepo.findAllByDepartment(department);
    }

    public RequestResult setTeacherInfoRequest(Long id, String info) {
        Teacher teacher = teacherRepo.findOne(id);
        if(teacher == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        info = info.trim();
        if(info.length() > Teacher.INFO_MAX_LENGTH) return RequestResult.ERROR_TOO_LONG;

        teacher.setInfo(info);
        teacherRepo.save(teacher);
        return RequestResult.SUCCESS_EDITED;
    }

    public void viewTeacherRequest(Long id, Model model) {
        Teacher teacher = teacherRepo.findOneFetchDepartment(id);
        if(teacher == null) throw new ResourceNotFoundException();

        model.addAttribute("teacher", teacher);
        model.addAttribute("department", teacher.getDepartment());
    }

    public List<Teacher> getAllOrderByFirstName() {
        return teacherRepo.findAllByOrderById();
    }
}