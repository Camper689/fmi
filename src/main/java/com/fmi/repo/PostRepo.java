package com.fmi.repo;

import com.fmi.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    @Query("FROM Post p LEFT JOIN FETCH p.album LEFT JOIN FETCH p.album.images WHERE p.id = :id")
    Post findOneFetchAlbum(@Param("id") Long id);

    List<Post> findAllByOrderByIdDesc();
}
