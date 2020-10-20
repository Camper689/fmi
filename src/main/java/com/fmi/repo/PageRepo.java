package com.fmi.repo;

import com.fmi.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PageRepo extends JpaRepository<Page, Long> {

    @Query("FROM Page p LEFT JOIN FETCH p.subPages WHERE p.id = :id")
    Page findOneFetchSubPages(@Param("id") Long id);

    @Query("FROM Page p LEFT JOIN FETCH p.section WHERE p.id = :id")
    Page findOneFetchSection(@Param("id") Long id);

    @Query("FROM Page p WHERE lower(p.name) = lower(:name)")
    Page findOneByNameIgnoreCase(@Param("name") String name);

    Page findOneByUrl(String url);

    List<Page> findAllBySectionId(Long id);
}
