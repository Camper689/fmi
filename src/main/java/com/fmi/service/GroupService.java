package com.fmi.service;

import com.fmi.controller.RequestResult;
import com.fmi.domain.Group;
import com.fmi.repo.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepo groupRepo;

    public List<Group> getAllOrderByName() {
        List<Group> all = groupRepo.findAll();
        all.sort((o1, o2) -> {
            if(o1.getCourse() != o2.getCourse())
                return (int) (o1.getCourse() - o2.getCourse());
            return o1.getName().compareTo(o2.getName());
        });
        return all;
    }

    public RequestResult addRequest(Long course, String name) {
        name = name.trim();
        if(name.isEmpty()) return RequestResult.ERROR_EMPTY;

        Group group = groupRepo.findOneByName(name);
        if(group != null) return RequestResult.ERROR_EXISTS;

        if(course < 1) course = 1L;

        group = new Group();
        group.setName(name);
        group.setCourse(course);
        groupRepo.save(group);

        return RequestResult.SUCCESS_ADDED;
    }

    public RequestResult editRequest(Long id, Long course, String name) {
        name = name.trim();
        if(name.isEmpty()) return RequestResult.ERROR_EMPTY;

        Group group = groupRepo.findOneByName(name);
        if(group != null && !group.getId().equals(id)) return RequestResult.ERROR_EXISTS;

        group = groupRepo.findOne(id);
        if(group == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        if(course < 1) course = 1L;
        group.setName(name);
        group.setCourse(course);
        groupRepo.save(group);

        return RequestResult.SUCCESS_EDITED;
    }

    public RequestResult deleteRequest(Long id) {
        Group group = groupRepo.findOne(id);
        if(group == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        groupRepo.delete(group);
        return RequestResult.SUCCESS_DELETED;
    }
}
