package com.example.gamecatalog.repository;

import com.example.gamecatalog.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
