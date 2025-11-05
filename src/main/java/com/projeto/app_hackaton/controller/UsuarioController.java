package com.projeto.app_hackaton.controller;

import com.projeto.app_hackaton.dto.usuario.UsuarioSearch;
import com.projeto.app_hackaton.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioSearch>> buscarTodos(){
        return ResponseEntity.ok().body(usuarioService.buscarTodosUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioSearch> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(usuarioService.buscarUsuarioPorId(id));
    }
}
