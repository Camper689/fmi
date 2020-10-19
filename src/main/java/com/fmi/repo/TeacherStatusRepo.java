package com.fmi.repo;

import com.fmi.domain.TeacherStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherStatusRepo extends JpaRepository<TeacherStatus, Long> {

    @Query("FROM TeacherStatus ts WHERE lower(ts.name) = lower(:name)")
    TeacherStatus findByNameIgnoreCase(@Param("name") String name);
}
