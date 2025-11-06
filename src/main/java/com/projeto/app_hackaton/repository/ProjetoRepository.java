package com.projeto.app_hackaton.repository;

import com.projeto.app_hackaton.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
