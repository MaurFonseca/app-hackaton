package com.projeto.app_hackaton.controller;

import com.projeto.app_hackaton.dto.equipe.CriarEquipe;
import com.projeto.app_hackaton.dto.equipe.EquipeAddMembro;
import com.projeto.app_hackaton.dto.equipe.EquipeResponse;
import com.projeto.app_hackaton.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @PostMapping("/criar")
    public ResponseEntity<EquipeResponse> criarEquipe(@RequestBody CriarEquipe criarEquipe){
        return ResponseEntity.status(201).body(equipeService.criarEquipe(criarEquipe));
    }

    @PostMapping("/{id}/add")
    public ResponseEntity adicionarMembro(@PathVariable Long id, @RequestBody EquipeAddMembro membro){
        equipeService.adicionarMembroEquipe(id, membro);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(equipeService.buscarPorId(id));
    }

}
