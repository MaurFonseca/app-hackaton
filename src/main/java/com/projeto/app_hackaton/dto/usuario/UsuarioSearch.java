package com.projeto.app_hackaton.dto.usuario;

import com.projeto.app_hackaton.model.enums.TipoUsuario;

public record UsuarioSearch (Long id, String nome, String email, TipoUsuario tipoUsuario) {
}
