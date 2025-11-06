package com.projeto.app_hackaton.config.teste;

import com.projeto.app_hackaton.model.Equipe;
import com.projeto.app_hackaton.model.Projeto;
import com.projeto.app_hackaton.model.Usuario;
import com.projeto.app_hackaton.model.enums.StatusEquipe;
import com.projeto.app_hackaton.model.enums.StatusProjeto;
import com.projeto.app_hackaton.model.enums.TipoUsuario;
import com.projeto.app_hackaton.repository.EquipeRepository;
import com.projeto.app_hackaton.repository.ProjetoRepository;
import com.projeto.app_hackaton.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Override
    public void run(String... args) throws Exception {
        // --- PARTICIPANTES ---

        String senha = new BCryptPasswordEncoder().encode("123456");

        Usuario u1 = new Usuario("Ana Silva", "ana@teste.com", senha, TipoUsuario.PARTICIPANTE);
        Usuario u2 = new Usuario("Bruno Lima", "bruno@teste.com", senha, TipoUsuario.PARTICIPANTE);
        Usuario u3 = new Usuario("Carla Souza", "carla@teste.com", senha, TipoUsuario.PARTICIPANTE);
        Usuario u4 = new Usuario("Diego Costa", "diego@teste.com", senha, TipoUsuario.PARTICIPANTE);
        Usuario u5 = new Usuario("Eduarda Alves", "eduarda@teste.com", senha, TipoUsuario.PARTICIPANTE);
        Usuario u6 = new Usuario("Felipe Rocha", "felipe@teste.com", senha, TipoUsuario.PARTICIPANTE);
        Usuario u7 = new Usuario("Gabriela Torres", "gabriela@teste.com", senha, TipoUsuario.PARTICIPANTE);

        // --- MENTORES ---
        Usuario u8 = new Usuario("Henrique Martins", "henrique@teste.com", senha, TipoUsuario.MENTOR);
        Usuario u9 = new Usuario("Isabela Castro", "isabela@teste.com", senha, TipoUsuario.MENTOR);

        // --- ADMIN ---
        Usuario u10 = new Usuario("João Admin", "admin@teste.com", senha, TipoUsuario.ADMIN);

        usuarioRepository.saveAll(Arrays.asList(u1,u2,u3,u4,u5,u6, u7, u8, u9, u10));

        Equipe e1 = new Equipe("Team Alpha", StatusEquipe.INSCRITA);
        Equipe e2 = new Equipe("Team Beta", StatusEquipe.APROVADA);
        Equipe e3 = new Equipe("Team Gamma", StatusEquipe.DESCLASSIFICADA);

        equipeRepository.saveAll(Arrays.asList(e1,e2,e3));
    }
}
