package com.fmi.repo;

import com.fmi.domain.PageSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PageSectionRepo extends JpaRepository<PageSection, Long> {

    @Query("FROM PageSection ps LEFT JOIN FETCH ps.pages LEFT JOIN FETCH ps.mainPage")
    Set<PageSection> findAllFetchPagesAndMainPage();

    @Query("FROM PageSection ps LEFT JOIN FETCH ps.mainPage")
    List<PageSection> findAllSectionsFetchMainPageOrderById();
}
