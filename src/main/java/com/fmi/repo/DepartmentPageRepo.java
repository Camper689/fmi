package com.fmi.repo;

import com.fmi.domain.Department;
import com.fmi.domain.DepartmentPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentPageRepo extends JpaRepository<DepartmentPage, Long> {

    @Query("FROM DepartmentPage dp WHERE dp.title = :title AND dp.section.id = :id")
    DepartmentPage findOneByTitleAndSectionId(@Param("title") String title, @Param("id") Long id);

    @Query("FROM DepartmentPage dp LEFT JOIN FETCH dp.section WHERE dp.id = :id")
    DepartmentPage findOneFetchSection(@Param("id") Long id);

    @Query("FROM DepartmentPage dp WHERE dp.department.id = :id")
    List<DepartmentPage> findAllByDepartmentId(@Param("id") Long id);

    @Query("FROM DepartmentPage dp LEFT JOIN FETCH dp.department WHERE dp.id = :id")
    DepartmentPage findOneFetchDepartment(@Param("id") Long id);
}
