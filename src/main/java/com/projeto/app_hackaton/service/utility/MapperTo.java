package com.projeto.app_hackaton.service.utility;

import com.projeto.app_hackaton.dto.usuario.UsuarioSearch;
import com.projeto.app_hackaton.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class MapperTo {

    public UsuarioSearch mapToUsuarioSearch(Usuario usuario){
        return new UsuarioSearch(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getTipo());
    }

}
