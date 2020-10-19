package com.fmi.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Entity
@Data
public class Timetable {

    @Id
    @GeneratedValue
    private Long id;

    private DayOfWeek day;

    @ManyToOne
    private Lesson lesson;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Group group;

    // Кожну неділю такі записи будуть видалятися
    private boolean oneWeekOnly = false;

    private int number = 1;
}
