package com.example.gamecatalog.repository;

import com.example.gamecatalog.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
