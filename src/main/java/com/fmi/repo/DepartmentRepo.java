package com.fmi.repo;

import com.fmi.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

    List<Department> findAllByOrderById();

    @Query("FROM Department d WHERE lower(d.name) = lower(:name)")
    Department findByNameIgnoreCase(@Param("name") String name);

    @Query("FROM Department d LEFT JOIN FETCH d.pages WHERE d.id = :id")
    Department findOneFetchPages(@Param("id") Long id);
}
