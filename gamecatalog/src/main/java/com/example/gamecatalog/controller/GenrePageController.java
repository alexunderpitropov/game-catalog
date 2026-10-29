package com.example.gamecatalog.controller;

import com.example.gamecatalog.dto.GenreDTO;
import com.example.gamecatalog.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenrePageController {

    private final GenreService genreService;

    @GetMapping
    public String listGenres(Model model) {
        List<GenreDTO> genres = genreService.getAll();
        model.addAttribute("genres", genres);
        return "genres";
    }

    @GetMapping("/add")
    public String showAddGenreForm(Model model) {
        model.addAttribute("genre", new GenreDTO());
        return "add-genre";
    }

    @PostMapping("/add")
    public String addGenre(@ModelAttribute GenreDTO genreDTO) {
        genreService.create(genreDTO);
        return "redirect:/genres";
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Long id) {
        genreService.delete(id);
        return "redirect:/genres";
    }
}
