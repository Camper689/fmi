package com.fmi.controller;

import com.fmi.domain.Album;
import com.fmi.domain.Category;
import com.fmi.domain.Post;
import com.fmi.service.AlbumService;
import com.fmi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public String postList(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            Model model
    ) {
        PageRequest pageable = new PageRequest(--page, 9, Sort.Direction.DESC, "id");

        Page<Post> result = postService.getPage(pageable);
        model.addAttribute("page", result);
        return "posts";
    }

    @GetMapping("/{id}")
    public String viewPost(
            @PathVariable("id") Long id,
            Model model
    ) {
        postService.viewPostRequest(id, model);
        return "post";
    }

    @GetMapping("/categories")
    public String viewCategories(
            Model model
    ) {
        model.addAttribute("allCategories", postService.getAllCategories());
        return "categories";
    }

    @GetMapping("/update")
    public String editor(
            @RequestParam(value = "post") Long postId,
            Model model
    ) {
        Post post = postService.getOneFetchAlbum(postId);
        if(post == null) throw new ResourceNotFoundException();

        model.addAttribute("post", post);
        model.addAttribute("allCategories", postService.getAllCategories());
        model.addAttribute("allAlbums", albumService.getAllOrderByIdDesc());

        return "postEditor";
    }

    @PostMapping("/setImage")
    public String setImage(
            @RequestParam("id") Post post,
            @RequestParam("image") MultipartFile image,
            RedirectAttributes redirectAttributes
    ) {
        postService.setImageRequest(post, image).write(redirectAttributes);
        return "redirect:/posts/update?post=" + post.getId();
    }

    @PostMapping("/setVisible")
    public String setVisible(
            @RequestParam("id") Post post
    ) {
        postService.setVisibleRequest(post);
        return "redirect:/posts/update?post=" + post.getId();
    }

    @PostMapping("/add")
    public String addPost(
            RedirectAttributes redirectAttributes
    ) {
        return "redirect:/posts/update?post=" + postService.addRequest();
    }

    @PostMapping("/update")
    public String updatePost(
            @RequestParam("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("body") String body,
            @RequestParam(value = "category", required = false) Category category,
            @RequestParam(value = "album", required = false) Album album,
            RedirectAttributes redirectAttributes
    ) {
        RequestResult write = postService.editRequest(id, title, body, category, album).write(redirectAttributes);
        if(!write.isSuccess()) {
            redirectAttributes.addFlashAttribute("title", title);
            redirectAttributes.addFlashAttribute("body", body);
        }
        return "redirect:/posts/update?post=" + id;
    }

    @PostMapping("/delete")
    public String deletePost(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        postService.deleteRequest(id).write(redirectAttributes);
        return "redirect:/posts";
    }

    @PostMapping("/addCategory")
    public String addCategory(
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ) {
        postService.addCategoryRequest(name).write(redirectAttributes);
        return "redirect:/posts/categories";
    }

    @PostMapping("/editCategory")
    public String editCategory(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ) {
        postService.editCategoryRequest(id, name).write(redirectAttributes);
        return "redirect:/posts/categories";
    }

    @PostMapping("/deleteCategory")
    public String deleteCategory(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        postService.deleteCategoryRequest(id).write(redirectAttributes);
        return "redirect:/posts/categories";
    }
}
