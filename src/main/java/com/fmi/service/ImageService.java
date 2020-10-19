package com.fmi.service;

import com.fmi.domain.Image;
import com.fmi.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Service
public class ImageService {

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${upload.maxSize}")
    private Long maxSize;

    @Autowired
    private ImageRepo imageRepo;

    private final Set<String> allowedExtensions = new HashSet<>(){{
        add("jpg");
        add("jpeg");
        add("png");
        add("bmp");
        add("tiff");
    }};

    @PostConstruct
    private void createUploadDir() {
        File file = new File(uploadPath);
        if(!file.exists()) file.mkdir();
    }

    public void delete(Image image) {
        new File(uploadPath + image.getFullName()).delete();
        if(image.getId() != null) imageRepo.delete(image);
    }

    private String generateRandomString(int length) {
        return IntStream
                .generate(() -> (char) (Math.random() * 26 + 97))
                .limit(length)
                .collect(StringBuilder::new, (b, val) -> b.append((char) val), StringBuilder::append)
                .toString();
    }

    public synchronized Pair<ImageUploadResult, List<Image>> saveToGallery(MultipartFile[] files) {
        for (MultipartFile file : files) {
            long size = file.getSize() / 1024;
            if(size > maxSize)
                return Pair.of(ImageUploadResult.ERROR_LARGE_FILE, new ArrayList<>());

            if(!allowedExtensions.contains(getExtension(file.getOriginalFilename())))
                return Pair.of(ImageUploadResult.ERROR_BAD_FILE_EXTENSION, new ArrayList<>());
        }

        List<Image> result = new ArrayList<>();
        for (MultipartFile file : files) {
            File newFile;
            String prefix;
            do {
                prefix = generateRandomString(10);
                newFile = new File(uploadPath + prefix + "__" + file.getOriginalFilename());
                System.out.println("generated string " + prefix);
            } while (newFile.exists());

            try {
                file.transferTo(newFile);

                Image image = new Image();
                image.setPrefix(prefix);
                image.setName(file.getOriginalFilename());
                result.add(image);
            } catch (IOException e) {
                result.forEach(this::delete);
                return Pair.of(ImageUploadResult.ERROR_SAVE, result);
            }
        }

        return Pair.of(ImageUploadResult.SUCCESS, result);
    }

    public String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.') + 1);
    }

    public Pair<ImageUploadResult, Image> saveAvatar(MultipartFile avatar) {
        long size = avatar.getSize() / 1024;
        if(size > maxSize) return Pair.of(ImageUploadResult.ERROR_LARGE_FILE, new Image());
        String extension = getExtension(avatar.getOriginalFilename());
        if(!allowedExtensions.contains(extension)) return Pair.of(ImageUploadResult.ERROR_BAD_FILE_EXTENSION, new Image());

        File newFile;
        String prefix, newName;
        do {
            prefix = generateRandomString(5);
            newName = "avatar" + "." + extension;
            newFile = new File(uploadPath + prefix + "__" + newName);
            System.out.println("generated string " + prefix);
        } while (newFile.exists());

        try {
            avatar.transferTo(newFile);

            Image image = new Image();
            image.setPrefix(prefix);
            image.setName(newName);
            return Pair.of(ImageUploadResult.SUCCESS, image);
        } catch (IOException e) {
            return Pair.of(ImageUploadResult.ERROR_SAVE, new Image());
        }
    }

    public void save(Image image) {
        imageRepo.save(image);
    }
}
