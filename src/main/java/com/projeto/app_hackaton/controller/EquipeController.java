package com.projeto.app_hackaton.controller;

import com.projeto.app_hackaton.dto.equipe.CriarEquipe;
import com.projeto.app_hackaton.dto.equipe.EquipeResponse;
import com.projeto.app_hackaton.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @PostMapping("/criar")
    public ResponseEntity<EquipeResponse> criarEquipe(@RequestBody CriarEquipe criarEquipe){
        return ResponseEntity.ok().body(equipeService.criarEquipe(criarEquipe));
    }

}
