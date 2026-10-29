package com.example.gamecatalog.controller;

import com.example.gamecatalog.dto.DeveloperDTO;
import com.example.gamecatalog.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/developers")
public class DeveloperPageController {

    private final DeveloperService developerService;

    @GetMapping
    public String listDevelopers(Model model) {
        List<DeveloperDTO> developers = developerService.getAll();
        model.addAttribute("developers", developers);
        return "developers";
    }

    @GetMapping("/add")
    public String showAddDeveloperForm(Model model) {
        model.addAttribute("developerDTO", new DeveloperDTO());
        return "add-developer";
    }

    @PostMapping("/add")
    public String addDeveloper(@ModelAttribute DeveloperDTO developerDTO) {
        developerService.create(developerDTO);
        return "redirect:/developers";
    }

    @GetMapping("/delete/{id}")
    public String deleteDeveloper(@PathVariable Long id) {
        developerService.delete(id);
        return "redirect:/developers";
    }
}
