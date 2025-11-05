package com.projeto.app_hackaton.dto.equipe;

import com.projeto.app_hackaton.model.enums.StatusEquipe;

import java.util.List;

public record EquipeResponse (Long id, String nome, StatusEquipe statusEquipe, List<UsuarioResEquipe> participantes, ProjetoResEquipe projeto){
}
