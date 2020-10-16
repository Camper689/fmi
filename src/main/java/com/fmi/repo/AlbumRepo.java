package com.fmi.repo;

import com.fmi.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepo extends JpaRepository<Album, Long> {

    List<Album> findAllByOrderByIdDesc();

    Album findOneByName(String name);
}
