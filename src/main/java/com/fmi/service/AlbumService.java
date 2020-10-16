package com.fmi.service;

import com.fmi.controller.RequestResult;
import com.fmi.domain.Album;
import com.fmi.repo.AlbumRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepo albumRepo;

    public List<Album> getAllOrderByIdDesc() {
        return albumRepo.findAllByOrderByIdDesc();
    }

    public RequestResult addRequest(String name) {
        name = name.trim();
        if(name.isEmpty()) return RequestResult.ERROR_EMPTY;

        Album oneByName = albumRepo.findOneByName(name);
        if(oneByName != null) return RequestResult.ERROR_EXISTS;

        Album album = new Album();
        album.setName(name);
        albumRepo.save(album);

        return RequestResult.SUCCESS_ADDED;
    }

    public RequestResult editRequest(Long id, String name) {
        name = name.trim();
        if(name.isEmpty()) return RequestResult.ERROR_EMPTY;

        Album album = albumRepo.findOneByName(name);
        if(album != null) return RequestResult.ERROR_EXISTS;

        album = albumRepo.findOne(id);
        if(album == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        album.setName(name);
        albumRepo.save(album);

        return RequestResult.SUCCESS_EDITED;
    }

    public RequestResult deleteRequest(Long id) {
        Album album = albumRepo.findOne(id);
        if(album == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        albumRepo.delete(album);

        return RequestResult.SUCCESS_DELETED;
    }
}
