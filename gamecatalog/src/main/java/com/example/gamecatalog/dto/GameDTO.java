package com.example.gamecatalog.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Set;
import java.util.List;

@Data
public class GameDTO {
    private Long id;
    private String title;
    private Double price;
    private LocalDate releaseDate;
    private Long developerId;
    private Set<Long> genreIds;

    private MultipartFile image;
    private String imageUrl;
    private String developerName;
    private List<String> genreNames;
}
