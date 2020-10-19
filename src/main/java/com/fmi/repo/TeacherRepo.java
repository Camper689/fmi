package com.fmi.repo;

import com.fmi.domain.Department;
import com.fmi.domain.Teacher;
import com.fmi.domain.TeacherStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {

    @Query("FROM Teacher t WHERE " +
            "lower(concat(t.firstName, ' ', t.middleName, ' ', t.lastName)) = " +
            "lower(concat(:firstName, ' ', :middleName, ' ', :lastName))")
    Teacher findByNameIgnoreCase(
            @Param("firstName") String firstName,
            @Param("middleName") String middleName,
            @Param("lastName") String lastName
    );

    List<Teacher> findAllByOrderById();

    List<Teacher> findAllByDepartmentOrderByFirstName(Department department);

    @Query("FROM Teacher t LEFT JOIN FETCH t.department WHERE t.id = :id")
    Teacher findOneByIdFetchDepartment(@Param("id") Long id);

    List<Teacher> findAllByStatus(TeacherStatus status);

    @Query("FROM Teacher t LEFT JOIN FETCH t.department WHERE t.id = :id")
    Teacher findOneFetchDepartment(@Param("id") Long id);

    List<Teacher> findAllByDepartment(Department department);
}
