package com.projeto.app_hackaton.service;

import com.projeto.app_hackaton.dto.usuario.UsuarioLogin;
import com.projeto.app_hackaton.dto.usuario.UsuarioRegister;
import com.projeto.app_hackaton.model.Usuario;
import com.projeto.app_hackaton.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void login(UsuarioLogin login){
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.login(), login.senha());

        var auth = this.authenticationManager.authenticate(usernamePassword);


    }

    public void regiter(UsuarioRegister register){
        if(usuarioRepository.findByEmail(register.email()) != null){
            throw new RuntimeException("Email já cadastrado!");
        }
        Usuario usuario = Usuario.builder()
                .nome(register.nome())
                .email(register.email())
                .senha(new BCryptPasswordEncoder().encode(register.senha()))
                .build();
        usuarioRepository.save(usuario);
    }
}
