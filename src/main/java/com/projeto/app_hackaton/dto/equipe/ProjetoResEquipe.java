package com.projeto.app_hackaton.dto.equipe;

// Classe para response de projeto dentro de uma equipe

import com.projeto.app_hackaton.model.enums.StatusProjeto;

public record ProjetoResEquipe (String nome, String gitUrl, StatusProjeto statusProjeto){
}
