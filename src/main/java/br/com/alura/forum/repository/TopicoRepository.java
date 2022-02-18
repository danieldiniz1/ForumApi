package br.com.alura.forum.repository;

import br.com.alura.forum.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

    List<Topico> findByCursoNome(String nomeCurso);
//    List<Topico> findByCurso_Nome(String nomeCurso); é a mesma coisa, caso tenha um atributo na classe com o nome

    @Query("SELECT t FROM Topico t Where t.curso.nome = :nomeCurso") //Usando query JPQL
    List<Topico> carregarTopicosFiltroNome(@Param("nomeCurso") String nomeCurso);
}
