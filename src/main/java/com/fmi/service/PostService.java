package com.fmi.service;

import com.fmi.controller.RequestResult;
import com.fmi.controller.ResourceNotFoundException;
import com.fmi.domain.Category;
import com.fmi.domain.Image;
import com.fmi.domain.Post;
import com.fmi.repo.CategoryRepo;
import com.fmi.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ImageService imageService;

    public Page<Post> getPage(PageRequest pageable) {
        return postRepo.findAllOrderByIdDesc(pageable);
    }

    public void viewPostRequest(Long id, Model model) {
        Post post = postRepo.findOne(id);
        if(post == null) throw new ResourceNotFoundException();

        model.addAttribute("post", post);
    }

    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    public RequestResult addCategoryRequest(String name) {
        name = name.trim();
        if (categoryRepo.findOneByName(name) != null) return RequestResult.ERROR_EXISTS;

        Category category = new Category();
        category.setName(name);
        categoryRepo.save(category);

        return RequestResult.SUCCESS_ADDED;
    }

    public RequestResult editCategoryRequest(Long id, String name) {
        name = name.trim();
        Category category = categoryRepo.findOneByName(name);
        if (category != null) return RequestResult.ERROR_EXISTS;

        category = categoryRepo.findOne(id);
        if(category == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        category.setName(name);
        categoryRepo.save(category);

        return RequestResult.SUCCESS_EDITED;
    }

    public RequestResult deleteCategoryRequest(Long id) {
        Category category = categoryRepo.findOne(id);
        if(category == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        categoryRepo.delete(category);
    }

    public void setVisibleRequest(Post post) {
        post.setVisible(!post.isVisible());
        postRepo.save(post);
    }

    public RequestResult setImageRequest(Post post, MultipartFile image) {
        Pair<ImageUploadResult, List<Image>> iuResult = imageService.saveToGallery(new MultipartFile[]{image});
        if(iuResult.getFirst() == ImageUploadResult.SUCCESS) {
            post.setImage(iuResult.getSecond().get(0));
            postRepo.save(post);
            return RequestResult.SUCCESS_EDITED;
        } else return RequestResult.valueOf(iuResult.getFirst());
    }

    public RequestResult addRequest(String title, String body, Category category) {
        title = title.trim(); body = body.trim();
        if(title.isEmpty()) return RequestResult.ERROR_EMPTY;

        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);
        post.setDate(LocalDate.now());
        post.setCategory(category);
        postRepo.save(post);
        return RequestResult.SUCCESS_ADDED;
    }

    public RequestResult editRequest(Long id, String title, String body, Category category) {
        title = title.trim(); body = body.trim();
        if(title.isEmpty()) return RequestResult.ERROR_EMPTY;

        Post post = postRepo.findOne(id);
        if(post == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        post.setTitle(title);
        post.setBody(body);
        post.setCategory(category);
        postRepo.save(post);
        return RequestResult.SUCCESS_EDITED;
    }

    public RequestResult deleteRequest(Long id) {
        Post post = postRepo.findOne(id);
        if(post == null) return RequestResult.ERROR_NOT_FOUND_BY_ID;

        postRepo.delete(post);
        return RequestResult.SUCCESS_DELETED;
    }
}
