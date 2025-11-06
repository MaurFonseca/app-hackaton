package com.projeto.app_hackaton.controller;

import com.projeto.app_hackaton.dto.projeto.ProjetoRequest;
import com.projeto.app_hackaton.dto.projeto.ProjetoResponse;
import com.projeto.app_hackaton.model.Usuario;
import com.projeto.app_hackaton.service.ProjetoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping("/submeter")
    public ResponseEntity<ProjetoResponse> submeterProjeto(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ProjetoRequest submeter){
        URI uri = UriComponentsBuilder.fromPath("/projetos/submeter").buildAndExpand().toUri();
        return ResponseEntity.created(uri).body(projetoService.submeterProjeto(userDetails, submeter));
    }

    @GetMapping()
    public ResponseEntity<ProjetoResponse> buscarMeuProjeto(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok().body(projetoService.buscarMeuProjeto(userDetails));
    }
}
