package com.fmi.service;

import com.fmi.controller.RequestResult;
import com.fmi.domain.Lesson;
import com.fmi.repo.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    @Autowired
    private LessonRepo lessonRepo;

    public List<Lesson> getAllOrderByName() {
        return lessonRepo.findAllByOrderByName();
    }

    public RequestResult addRequest(String name, String shortName) {
        name = name.trim(); shortName = shortName.trim();
        if(name.isEmpty() || shortName.isEmpty()) return RequestResult.ERROR_EMPTY;

        Lesson lesson = lessonRepo.findOneByNameIgnoreCase(name);
        if(lesson != null) return RequestResult.ERROR_EXISTS;

        lesson = new Lesson();
        lesson.setName(name);
        lesson.setShortName(shortName);
        lessonRepo.save(lesson);

        return RequestResult.SUCCESS_ADDED;
    }

    public RequestResult editRequest(Long id, String name, String shortName) {
        name = name.trim(); shortName = shortName.trim();
        if(name.isEmpty() || shortName.isEmpty()) return RequestResult.ERROR_EMPTY;

        Lesson lesson = lessonRepo.findOneByNameIgnoreCase(name);
        if(lesson != null && !lesson.getId().equals(id)) return RequestResult.ERROR_EXISTS;

        lesson = lessonRepo.findOne(id);
        if(lesson == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        lesson.setName(name);
        lesson.setShortName(shortName);
        lessonRepo.save(lesson);

        return RequestResult.SUCCESS_EDITED;
    }

    public RequestResult deleteRequest(Long id) {
        Lesson lesson = lessonRepo.findOne(id);
        if(lesson == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        lessonRepo.delete(lesson);
        return RequestResult.SUCCESS_DELETED;
    }
}