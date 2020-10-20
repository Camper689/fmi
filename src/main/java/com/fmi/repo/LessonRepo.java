package com.fmi.repo;

import com.fmi.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepo extends JpaRepository<Lesson, Long> {
    List<Lesson> findAllByOrderByName();

    @Query("FROM Lesson l WHERE lower(l.name) = lower(:name)")
    Lesson findOneByNameIgnoreCase(@Param("name") String name);
}
