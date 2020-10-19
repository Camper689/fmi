package com.fmi.repo;

import com.fmi.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepo extends JpaRepository<Album, Long> {

    List<Album> findAllByOrderByIdDesc();

    @Query("FROM Album a WHERE lower(a.name) = lower(:name)")
    Album findByNameIgnoreCase(@Param("name") String name);

    @Query("FROM Album a LEFT JOIN FETCH a.images WHERE a.id = :id")
    Album findOneFetchImages(@Param("id") Long id);
}
