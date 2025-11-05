package com.projeto.app_hackaton.model;

import com.projeto.app_hackaton.model.enums.StatusProjeto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private String urlGit;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StatusProjeto statusProjeto = StatusProjeto.SUBMETIDO;

    @OneToOne
    @MapsId
    private Equipe equipe;

    @OneToOne(mappedBy = "projeto")
    private Avaliacao avaliacao;
}
