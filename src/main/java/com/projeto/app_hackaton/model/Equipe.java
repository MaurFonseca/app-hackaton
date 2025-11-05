package com.projeto.app_hackaton.model;

import com.projeto.app_hackaton.model.enums.StatusEquipe;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StatusEquipe statusEquipe = StatusEquipe.INSCRITA;

    @OneToMany(mappedBy = "equipe")
    private List<Usuario> participantes;

    @OneToOne(mappedBy = "equipe", cascade = CascadeType.ALL)
    private Projeto projeto;
}
