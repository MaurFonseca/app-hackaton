package com.projeto.app_hackaton.controller;

import com.projeto.app_hackaton.dto.usuario.UsuarioLogin;
import com.projeto.app_hackaton.dto.usuario.UsuarioRegister;
import com.projeto.app_hackaton.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UsuarioLogin login){
        authenticationService.login(login);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsuarioRegister register){
        authenticationService.regiter(register);
        URI uri = UriComponentsBuilder.fromPath("/auth/register").buildAndExpand().toUri();
        return ResponseEntity.created(uri).build();
    }
}
