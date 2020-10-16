package com.fmi.controller;

import com.fmi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GalleryController {

    @Autowired
    private AlbumService albumService;

    @GetMapping("/gallery")
    public String listGalleries(
            Model model
    ) {
        model.addAttribute("allAlbums", albumService.getAllOrderByIdDesc());
        return "galleries";
    }

    @PostMapping("/album/add")
    public String addAlbum(
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ) {
        albumService.addRequest(name).write(redirectAttributes);
        return "redirect:/gallery";
    }

    @PostMapping("/album/edit")
    public String editAlbum(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ) {
        albumService.editRequest(id, name).write(redirectAttributes);
        return "redirect:/gallery";
    }

    @PostMapping("/album/delete")
    public String deleteAlbum(
            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        albumService.deleteRequest(id).write(redirectAttributes);
        return "redirect:/gallery";
    }
}