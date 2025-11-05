package com.projeto.app_hackaton.controller;

import com.projeto.app_hackaton.dto.UsuarioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(UsuarioLogin login){
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.login(), login.senha());

        var auth = this.authenticationManager.authenticate(usernamePassword);
        
        return ResponseEntity.ok().build();
    }
}
