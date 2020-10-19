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
    }};

    @PostConstruct
    private void createUploadDirIfNotExists() {
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

    private ImageUploadResult checkFile(MultipartFile file) {
        long size = file.getSize() / 1024;

        if(size > maxSize) return ImageUploadResult.ERROR_LARGE_FILE;
        if(!allowedExtensions.contains(getExtension(file.getOriginalFilename()))) return ImageUploadResult.ERROR_BAD_FILE_EXTENSION;

        return null;
    }

    private Pair<File, Image> getNewFileAndImage(int hashLength, String filename) {
        String prefix; File newFile;
        do {
            prefix = generateRandomString(hashLength);
            newFile = new File(uploadPath + prefix + "__" + filename);
            System.out.println("generated string " + prefix);
        } while (newFile.exists());

        Image image = new Image();
        image.setPrefix(prefix);
        image.setName(filename);

        return Pair.of(newFile, image);
    }

    public String uploadApi(MultipartFile file) {
        if(checkFile(file) != null) return null;
        Pair<File, Image> result = getNewFileAndImage(10, file.getOriginalFilename());

        try {
            file.transferTo(result.getFirst());
            imageRepo.save(result.getSecond());
            return "/image/" + result.getSecond().getFullName();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Завантажує масив файлів і повертає пару <Результат, Масив сутностей>
    // Масив сутностей треба зберегти
    public synchronized Pair<ImageUploadResult, List<Image>> saveToGallery(MultipartFile[] files) {
        for (MultipartFile file : files) {
            ImageUploadResult error = checkFile(file);
            if(error != null) return Pair.of(error, new ArrayList<>());
        }

        List<Image> result = new ArrayList<>();
        for (MultipartFile file : files) {
            Pair<File, Image> newFileAndImage = getNewFileAndImage(9, file.getOriginalFilename());

            try {
                file.transferTo(newFileAndImage.getFirst());
                result.add(newFileAndImage.getSecond());
            } catch (IOException e) {
                result.forEach(this::delete);
                return Pair.of(ImageUploadResult.ERROR_SAVE, result);
            }
        }

        return Pair.of(ImageUploadResult.SUCCESS, result);
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.') + 1);
    }

    public Pair<ImageUploadResult, Image> saveAvatar(MultipartFile avatar) {
        ImageUploadResult imageUploadResult = checkFile(avatar);
        if(imageUploadResult != null) return Pair.of(imageUploadResult, new Image());

        Pair<File, Image> avatarNew = getNewFileAndImage(5, "avatar");

        try {
            avatar.transferTo(avatarNew.getFirst());
            return Pair.of(ImageUploadResult.SUCCESS, avatarNew.getSecond());
        } catch (IOException e) {
            return Pair.of(ImageUploadResult.ERROR_SAVE, new Image());
        }
    }

    public void save(Image image) {
        imageRepo.save(image);
    }
}
