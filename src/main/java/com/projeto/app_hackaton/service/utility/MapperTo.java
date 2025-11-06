package com.projeto.app_hackaton.service.utility;

import com.projeto.app_hackaton.dto.equipe.EquipeResponse;
import com.projeto.app_hackaton.dto.equipe.ProjetoResEquipe;
import com.projeto.app_hackaton.dto.equipe.UsuarioResEquipe;
import com.projeto.app_hackaton.dto.projeto.ProjetoResponse;
import com.projeto.app_hackaton.dto.usuario.UsuarioSearch;
import com.projeto.app_hackaton.model.Equipe;
import com.projeto.app_hackaton.model.Projeto;
import com.projeto.app_hackaton.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapperTo {

    public UsuarioSearch mapToUsuarioSearch(Usuario usuario){
        return new UsuarioSearch(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTipo());
    }

    public EquipeResponse mapToEquipeResponse(Equipe equipe){
        List<UsuarioResEquipe> participantes = (equipe.getParticipantes() == null)
                ? List.of()
                : equipe.getParticipantes().stream().map(this::toUsuarioResEquipe).toList();

        ProjetoResEquipe projeto = (equipe.getProjeto() == null)
                ? null
                : toProjetoResEquipe(equipe.getProjeto());

        return new EquipeResponse(
                equipe.getId(),
                equipe.getNome(),
                equipe.getStatusEquipe(),
                participantes,
                projeto
        );
    }

    private UsuarioResEquipe toUsuarioResEquipe(Usuario usuario){
        return new UsuarioResEquipe(usuario.getId(), usuario.getNome());
    }

    private ProjetoResEquipe toProjetoResEquipe(Projeto projeto){
        return new ProjetoResEquipe(projeto.getNome(), projeto.getUrlGit(), projeto.getStatusProjeto());
    }

    public ProjetoResponse mapToProjetoResponse(Projeto projeto){
        return new ProjetoResponse(projeto.getId(), projeto.getNome(), projeto.getUrlGit(), projeto.getDescricao(), projeto.getStatusProjeto(), projeto.getEquipe().getId());
    }

}
