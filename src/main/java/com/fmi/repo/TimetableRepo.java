package com.fmi.repo;

import com.fmi.domain.Group;
import com.fmi.domain.Teacher;
import com.fmi.domain.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimetableRepo extends JpaRepository<Timetable, Long> {

    List<Timetable> findAllByGroup(Group group);

    List<Timetable> findAllByTeacher(Teacher teacher);
}
