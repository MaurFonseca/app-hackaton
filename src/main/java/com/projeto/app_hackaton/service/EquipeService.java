package com.projeto.app_hackaton.service;

import com.projeto.app_hackaton.dto.equipe.CriarEquipe;
import com.projeto.app_hackaton.dto.equipe.EquipeResponse;
import com.projeto.app_hackaton.model.Equipe;
import com.projeto.app_hackaton.repository.EquipeRepository;
import com.projeto.app_hackaton.service.utility.MapperTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private MapperTo mapperTo;


    public EquipeResponse criarEquipe(CriarEquipe criarEquipe){
        Equipe equipe = Equipe.builder()
                .nome(criarEquipe.nome())
                .build();
        equipeRepository.save(equipe);
        return mapperTo.mapToEquipeResponse(equipe);
    }

}
