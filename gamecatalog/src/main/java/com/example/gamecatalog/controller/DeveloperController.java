package com.example.gamecatalog.controller;

import com.example.gamecatalog.dto.DeveloperDTO;
import com.example.gamecatalog.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/developers")
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @GetMapping
    public List<DeveloperDTO> getAll() {
        return developerService.getAll();
    }


    @PostMapping
    public void create(@RequestBody DeveloperDTO developerDTO) {
        developerService.create(developerDTO);
    }
}
