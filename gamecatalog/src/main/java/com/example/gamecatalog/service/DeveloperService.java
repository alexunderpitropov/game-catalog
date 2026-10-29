package com.example.gamecatalog.service;

import com.example.gamecatalog.dto.DeveloperDTO;
import com.example.gamecatalog.entity.Developer;
import com.example.gamecatalog.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public List<DeveloperDTO> getAll() {
        return developerRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void create(DeveloperDTO dto) {
        Developer developer = new Developer();
        developer.setName(dto.getName());
        developerRepository.save(developer);
    }

    public void delete(Long id) {
        developerRepository.deleteById(id);
    }

    private DeveloperDTO toDTO(Developer developer) {
        DeveloperDTO dto = new DeveloperDTO();
        dto.setId(developer.getId());
        dto.setName(developer.getName());
        return dto;
    }
}
