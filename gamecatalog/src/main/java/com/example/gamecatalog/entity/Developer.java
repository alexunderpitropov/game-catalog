package com.example.gamecatalog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "developers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL)
    private List<Game> games;
}
