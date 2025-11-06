package com.projeto.app_hackaton.service;

import com.projeto.app_hackaton.dto.projeto.ProjetoRequest;
import com.projeto.app_hackaton.dto.projeto.ProjetoResponse;
import com.projeto.app_hackaton.model.Equipe;
import com.projeto.app_hackaton.model.Projeto;
import com.projeto.app_hackaton.model.Usuario;
import com.projeto.app_hackaton.model.enums.StatusProjeto;
import com.projeto.app_hackaton.repository.EquipeRepository;
import com.projeto.app_hackaton.repository.ProjetoRepository;
import com.projeto.app_hackaton.repository.UsuarioRepository;
import com.projeto.app_hackaton.service.utility.MapperTo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MapperTo mapperTo;


    private Usuario recoverUsuario(UserDetails userDetails){
        return (Usuario) usuarioRepository.findByEmail(userDetails.getUsername());
    }


    public ProjetoResponse submeterProjeto(UserDetails userDetails, ProjetoRequest request){
        Usuario usuario = recoverUsuario(userDetails);
        Equipe equipe = equipeRepository.findById(usuario.getEquipe().getId()).orElseThrow(()-> new RuntimeException("Equipe não encontrada"));
        Projeto projeto = Projeto.builder()
                .nome(request.nome())
                .descricao(request.descricao())
                .urlGit(request.urlGit())
                .statusProjeto(StatusProjeto.SUBMETIDO)
                .equipe(equipe)
                .build();
        equipe.setProjeto(projeto);
        projetoRepository.save(projeto);
        equipeRepository.save(equipe);
        return mapperTo.mapToProjetoResponse(projeto);
    }

    public ProjetoResponse buscarMeuProjeto(UserDetails userDetails){
        Usuario usuario = recoverUsuario(userDetails);
        Equipe equipe = equipeRepository.findById(usuario.getEquipe().getId()).orElseThrow(()-> new RuntimeException("Equipe não encontrada"));
        Projeto projeto = equipe.getProjeto();
        return mapperTo.mapToProjetoResponse(projeto);
    }
}
