package com.example.gamecatalog.service;

import com.example.gamecatalog.dto.GenreDTO;
import com.example.gamecatalog.entity.Genre;
import com.example.gamecatalog.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<GenreDTO> getAll() {
        return genreRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void create(GenreDTO dto) {
        Genre genre = new Genre();
        genre.setName(dto.getName());
        genreRepository.save(genre);
    }

    public void delete(Long id) {
        genreRepository.deleteById(id);
    }

    private GenreDTO toDTO(Genre genre) {
        GenreDTO dto = new GenreDTO();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        return dto;
    }
}
