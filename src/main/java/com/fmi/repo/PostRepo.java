package com.fmi.repo;

import com.fmi.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {

    Page<Post> findAllOrderByIdDesc(PageRequest pageable);
}
