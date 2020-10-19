package com.fmi.repo;

import com.fmi.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepo extends JpaRepository<Image, Long> {

    @Query("FROM Image i LEFT JOIN FETCH i.album WHERE i.id = :id")
    Image findOneFetchAlbum(@Param("id") Long id);

    @Query("FROM Image i WHERE i.id = (SELECT max(i.id) FROM Image i WHERE :id = i.album.id AND i.id < :exceptId)")
    Image findLastByAlbum(@Param("id") Long id, @Param("exceptId") Long imageId);
}
