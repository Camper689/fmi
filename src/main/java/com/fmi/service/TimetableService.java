package com.fmi.service;

import com.fmi.controller.RequestResult;
import com.fmi.domain.Group;
import com.fmi.domain.Lesson;
import com.fmi.domain.Teacher;
import com.fmi.domain.Timetable;
import com.fmi.repo.TimetableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;

import static com.fmi.controller.RequestResult.SUCCESS;

@Service
public class TimetableService {

    @Autowired
    private TimetableRepo timetableRepo;

    private final static List<DayOfWeek> days = Arrays.asList(
            DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SUNDAY
    );

    private final static Map<DayOfWeek, Set<Timetable>> emptyMap = new LinkedHashMap<>();
    static {
        for (DayOfWeek day : days) {
            emptyMap.put(day, new TreeSet<>(Comparator.comparing(Timetable::getNumber)));
        }
    };


    public Map<DayOfWeek, Set<Timetable>> getTimetablesForGroup(Group group) {
        var result = new LinkedHashMap<>(emptyMap);

        timetableRepo.findAllByGroup(group).forEach(timetable -> {
            result.get(timetable.getDay()).add(timetable);
        });

        return result;
    }

    public Map<DayOfWeek, Set<Timetable>> getTimetablesForTeacher(Teacher teacher) {
        var result = new LinkedHashMap<>(emptyMap);

        timetableRepo.findAllByTeacher(teacher).forEach(timetable -> {
            result.get(timetable.getDay()).add(timetable);
        });

        return result;

    }

    public RequestResult deleteRequest(Long id) {
        Timetable timetable = timetableRepo.findOne(id);
        if(timetable == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        timetableRepo.delete(timetable);
        return SUCCESS;
    }

    public RequestResult setRequest(Long id, Group group, Lesson lesson, Teacher teacher, boolean oneWeekOnly) {
        Timetable timetable = id == null ? new Timetable() : timetableRepo.getOne(id);
        if(timetable == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        timetable.setGroup(group);
        timetable.setLesson(lesson);
        timetable.setTeacher(teacher);
        timetable.setOneWeekOnly(oneWeekOnly);

        timetableRepo.save(timetable);

        return SUCCESS;
    }
}
