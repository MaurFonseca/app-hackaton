package com.projeto.app_hackaton.dto.projeto;

import com.projeto.app_hackaton.model.enums.StatusProjeto;

public record ProjetoResponse(Long id, String nome, String urlGit, String descricao, StatusProjeto statusProjeto, Long idEquipe) {
}
