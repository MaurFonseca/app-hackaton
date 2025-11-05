package com.projeto.app_hackaton.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    private Projeto projeto;

    private int nota;

    private String comentario;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Usuario mentor;
}
