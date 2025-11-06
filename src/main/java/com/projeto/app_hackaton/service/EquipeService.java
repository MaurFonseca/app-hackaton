package com.projeto.app_hackaton.service;

import com.projeto.app_hackaton.dto.equipe.CriarEquipe;
import com.projeto.app_hackaton.dto.equipe.EquipeAddMembro;
import com.projeto.app_hackaton.dto.equipe.EquipeResponse;
import com.projeto.app_hackaton.model.Equipe;
import com.projeto.app_hackaton.model.Usuario;
import com.projeto.app_hackaton.model.enums.TipoUsuario;
import com.projeto.app_hackaton.repository.EquipeRepository;
import com.projeto.app_hackaton.repository.UsuarioRepository;
import com.projeto.app_hackaton.service.utility.MapperTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private MapperTo mapperTo;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public EquipeResponse buscarPorId(Long id){
        return mapperTo.mapToEquipeResponse(equipeRepository.findById(id).orElseThrow(()-> new RuntimeException("Equipe não encontrada")));
    }


    public EquipeResponse criarEquipe(CriarEquipe criarEquipe){
        Equipe equipe = Equipe.builder()
                .nome(criarEquipe.nome())
                .build();
        equipeRepository.save(equipe);
        return mapperTo.mapToEquipeResponse(equipe);
    }

    public void adicionarMembroEquipe(Long idEquipe, EquipeAddMembro idParticipante){
        Equipe equipe = equipeRepository.findById(idEquipe).orElseThrow(() -> new RuntimeException("Equipe não encontrada"));
        Usuario participante = usuarioRepository.findById(idParticipante.idParticipante()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (participante.getTipo() != TipoUsuario.PARTICIPANTE){
            throw new RuntimeException("Este usuário não é do tipo PARTICIPANTE");
        }
        if (equipe.getParticipantes().size() >= 5){
            throw new RuntimeException("Equipe cheia");
        }
        for(Usuario usuario : equipe.getParticipantes()){
            if(usuario.getId().equals(participante.getId())){
                throw new RuntimeException("Usuario já cadastrado nessa equipe");
            }
        }
        if(participante.getEquipe() != null){
            throw new RuntimeException("Usuário já pertence a uma equipe");
        }
        participante.setEquipe(equipe);
        usuarioRepository.save(participante);
    }

}
