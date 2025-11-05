package com.projeto.app_hackaton.repository;
import com.projeto.app_hackaton.model.Usuario;
import com.projeto.app_hackaton.model.enums.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByEmail(String email);

    List<Usuario> findByTipo(TipoUsuario tipoUsuario);
}
