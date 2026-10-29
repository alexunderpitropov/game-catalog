package com.example.gamecatalog.controller;

import com.example.gamecatalog.dto.GameDTO;
import com.example.gamecatalog.service.DeveloperService;
import com.example.gamecatalog.service.GameService;
import com.example.gamecatalog.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;
    private final DeveloperService developerService;
    private final GenreService genreService;

    @GetMapping
    public String listGames(@RequestParam(value = "sort", required = false) String sort, Model model) {
        List<GameDTO> games = gameService.getAllSorted(sort);
        model.addAttribute("games", games);
        model.addAttribute("selectedSort", sort);
        return "games";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("gameDTO", new GameDTO());
        model.addAttribute("developers", developerService.getAll());
        model.addAttribute("genres", genreService.getAll());
        return "add-game";
    }

    @PostMapping("/add")
    public String addGame(@ModelAttribute GameDTO gameDTO,
                          @RequestParam("image") MultipartFile image) {
        gameDTO.setImage(image);
        gameService.create(gameDTO);
        return "redirect:/games";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        GameDTO gameDTO = gameService.getById(id);
        if (gameDTO == null) {
            return "redirect:/games";
        }
        model.addAttribute("gameDTO", gameDTO);
        model.addAttribute("developers", developerService.getAll());
        model.addAttribute("genres", genreService.getAll());
        return "edit-game";
    }

    @PostMapping("/edit/{id}")
    public String editGame(@PathVariable Long id,
                           @ModelAttribute GameDTO gameDTO,
                           @RequestParam("image") MultipartFile image) {
        gameDTO.setImage(image);
        gameService.update(id, gameDTO);
        return "redirect:/games";
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable Long id) {
        gameService.delete(id);
        return "redirect:/games";
    }
}
