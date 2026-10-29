package com.example.gamecatalog.repository;

import com.example.gamecatalog.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
