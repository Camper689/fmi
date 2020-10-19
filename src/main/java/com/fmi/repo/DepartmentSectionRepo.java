package com.fmi.repo;

import com.fmi.domain.DepartmentSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface DepartmentSectionRepo extends JpaRepository<DepartmentSection, Long> {

    @Query("FROM DepartmentSection ds LEFT JOIN FETCH ds.department WHERE ds.id = :id")
    DepartmentSection findOneFetchDepartment(@Param("id") Long id);

    @Query("FROM DepartmentSection ds LEFT JOIN FETCH ds.pages WHERE ds.department.id = :id ORDER BY ds.id")
    Set<DepartmentSection> findAllByDepartmentFetchPagesOrderById(@Param("id") Long departmentId);
}
