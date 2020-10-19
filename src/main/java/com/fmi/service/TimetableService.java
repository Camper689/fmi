package com.fmi.service;

import com.fmi.domain.Group;
import com.fmi.domain.Teacher;
import com.fmi.domain.Timetable;
import com.fmi.repo.TimetableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;

@Service
public class TimetableService {

    @Autowired
    private TimetableRepo timetableRepo;

    private final static Comparator<Timetable> timetableComparator = Comparator.comparing(Timetable::getNumber);
    private final static List<DayOfWeek> days = Arrays.asList(DayOfWeek.values());
    static {{
        days.remove(DayOfWeek.SUNDAY);
    }}

    private final static Map<DayOfWeek, Set<Timetable>> emptyMap = new LinkedHashMap<>() {{
        for (DayOfWeek day : days) {
            emptyMap.put(day, new TreeSet<>(timetableComparator));
        }
    }};


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
}
