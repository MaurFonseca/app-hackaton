package com.projeto.app_hackaton.service;

import com.projeto.app_hackaton.dto.usuario.UsuarioSearch;
import com.projeto.app_hackaton.model.enums.TipoUsuario;
import com.projeto.app_hackaton.repository.UsuarioRepository;
import com.projeto.app_hackaton.service.utility.MapperTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MapperTo mapperTo;

    public List<UsuarioSearch> buscarTodosUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(x -> mapperTo.mapToUsuarioSearch(x))
                .toList();
    }

    public UsuarioSearch buscarUsuarioPorId(Long id){
        return mapperTo.mapToUsuarioSearch(usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
    }


}
