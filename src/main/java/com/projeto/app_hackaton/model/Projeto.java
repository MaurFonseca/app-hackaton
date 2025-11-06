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
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @OneToOne(mappedBy = "projeto")
    private Avaliacao avaliacao;

    public Projeto(String nome, String descricao, String urlGit, StatusProjeto statusProjeto, Equipe equipe) {
        this.nome = nome;
        this.descricao = descricao;
        this.urlGit = urlGit;
        this.statusProjeto = statusProjeto;
        this.equipe = equipe;
    }
}
