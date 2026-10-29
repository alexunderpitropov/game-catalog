package com.example.gamecatalog.service;

import com.example.gamecatalog.dto.GameDTO;
import com.example.gamecatalog.entity.Developer;
import com.example.gamecatalog.entity.Game;
import com.example.gamecatalog.entity.Genre;
import com.example.gamecatalog.repository.DeveloperRepository;
import com.example.gamecatalog.repository.GameRepository;
import com.example.gamecatalog.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final DeveloperRepository developerRepository;
    private final GenreRepository genreRepository;

    public List<GameDTO> getAll() {
        return gameRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<GameDTO> getAllSorted(String sort) {
        List<GameDTO> games = getAll();

        if (sort == null || sort.isBlank()) return games;

        Comparator<GameDTO> comparator = switch (sort) {
            case "title" -> Comparator.comparing(GameDTO::getTitle, String.CASE_INSENSITIVE_ORDER);
            case "price" -> Comparator.comparing(GameDTO::getPrice, Comparator.nullsLast(Double::compareTo));
            case "releaseDate" -> Comparator.comparing(GameDTO::getReleaseDate, Comparator.nullsLast(Comparator.naturalOrder()));
            default -> Comparator.comparing(GameDTO::getId);
        };

        return games.stream().sorted(comparator).collect(Collectors.toList());
    }

    public GameDTO getById(Long id) {
        return gameRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    public void create(GameDTO dto) {
        Game game = new Game();
        fillEntity(game, dto);
        gameRepository.save(game);
    }

    public void update(Long id, GameDTO dto) {
        Game game = gameRepository.findById(id).orElseThrow();
        fillEntity(game, dto);
        gameRepository.save(game);
    }

    public void delete(Long id) {
        gameRepository.deleteById(id);
    }

    private void fillEntity(Game game, GameDTO dto) {
        game.setTitle(dto.getTitle());
        game.setPrice(dto.getPrice());
        game.setReleaseDate(dto.getReleaseDate());

        Developer dev = developerRepository.findById(dto.getDeveloperId())
                .orElseThrow(() -> new RuntimeException("Developer not found"));
        game.setDeveloper(dev);

        Set<Genre> genres = genreRepository.findAllById(dto.getGenreIds())
                .stream().collect(Collectors.toSet());
        game.setGenres(genres);

        MultipartFile image = dto.getImage();
        if (image != null && !image.isEmpty()) {
            String filename = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path uploadPath = Paths.get("uploads");

            try {
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(filename);
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                game.setImagePath("/uploads/" + filename);
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при сохранении изображения", e);
            }
        }
    }

    private GameDTO toDTO(Game game) {
        GameDTO dto = new GameDTO();
        dto.setId(game.getId());
        dto.setTitle(game.getTitle());
        dto.setPrice(game.getPrice());
        dto.setReleaseDate(game.getReleaseDate());
        dto.setDeveloperId(game.getDeveloper().getId());
        dto.setGenreIds(game.getGenres().stream().map(Genre::getId).collect(Collectors.toSet()));
        dto.setImageUrl(game.getImagePath());
        dto.setDeveloperName(game.getDeveloper().getName());
        dto.setGenreNames(game.getGenres().stream().map(Genre::getName).collect(Collectors.toList()));
        return dto;
    }
}
