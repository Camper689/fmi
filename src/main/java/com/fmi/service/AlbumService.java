package com.fmi.service;

import com.fmi.controller.RequestResult;
import com.fmi.controller.ResourceNotFoundException;
import com.fmi.domain.Album;
import com.fmi.domain.Image;
import com.fmi.repo.AlbumRepo;
import com.fmi.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepo albumRepo;

    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CacheService cacheService;

    public List<Album> getAllOrderByIdDesc() {
        return albumRepo.findAllByOrderByIdDesc();
    }

    public RequestResult addRequest(String name) {
        name = name.trim();
        if(name.isEmpty()) return RequestResult.ERROR_EMPTY;

        Album oneByName = albumRepo.findByNameIgnoreCase(name);
        if(oneByName != null) return RequestResult.ERROR_EXISTS;

        Album album = new Album();
        album.setName(name);
        albumRepo.save(album);

        cacheService.albumAdded(album);

        return RequestResult.SUCCESS_ADDED;
    }

    public RequestResult editRequest(Long id, String name) {
        name = name.trim();
        if(name.isEmpty()) return RequestResult.ERROR_EMPTY;

        Album album = albumRepo.findByNameIgnoreCase(name);
        if(album != null) return RequestResult.ERROR_EXISTS;

        album = albumRepo.findOne(id);
        if(album == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        album.setName(name);
        albumRepo.save(album);

        cacheService.albumEdited(album);

        return RequestResult.SUCCESS_EDITED;
    }

    public RequestResult deleteRequest(Long id) {
        Album album = albumRepo.findOneFetchImages(id);
        if(album == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        album.getImages().forEach(imageService::delete);
        cacheService.albumDeleted(id);

        return RequestResult.SUCCESS_DELETED;
    }

    public void viewAlbumRequest(Long id, Model model) {
        Album one = albumRepo.findOneFetchImages(id);
        if(one == null) throw new ResourceNotFoundException();

        model.addAttribute("album", one);
    }

    public RequestResult addPhotosRequest(Long id, MultipartFile[] files) {
        if(files.length == 0) return RequestResult.ERROR_EMPTY;

        Album album = albumRepo.findOne(id);
        if(album == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        Pair<ImageUploadResult, List<Image>> imageUploadResultPair = imageService.saveToGallery(files);
        switch (imageUploadResultPair.getFirst()) {
            case ERROR_BAD_FILE_EXTENSION: return RequestResult.ERROR_BAD_FILE_EXTENSION;
            case ERROR_LARGE_FILE: return RequestResult.ERROR_LARGE_FILE;
            case ERROR_SAVE: return RequestResult.ERROR_SAVE_IMAGE;
            case SUCCESS: {
                for (Image image : imageUploadResultPair.getSecond()) {
                    image.setAlbum(album);
                    imageRepo.save(image);

                    album.setLastImage(image);
                }

                albumRepo.save(album);

                return RequestResult.SUCCESS_IMAGES_ADDED;
            }
            default:
                return RequestResult.ERROR_EMPTY;
        }
    }

    public RequestResult deletePhotoRequest(Long id) {
        Image image = imageRepo.findOneFetchAlbum(id);
        if(image == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        Album album = image.getAlbum();
        if(album != null && album.getLastImage().getPrefix().equals(image.getPrefix())) {
            Image lastByAlbum = imageRepo.findLastByAlbum(album.getId(), image.getId());
            album.setLastImage(lastByAlbum);
            albumRepo.save(album);
        }

        imageService.delete(image);
        return RequestResult.SUCCESS_DELETED;
    }
}
