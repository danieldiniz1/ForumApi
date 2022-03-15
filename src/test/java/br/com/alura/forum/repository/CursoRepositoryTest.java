package br.com.alura.forum.repository;

import br.com.alura.forum.model.Curso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    @Test
    public void deveCarregarUmCursoAoBuscarPeloNome(){
        String nomeCurso = "HTML 5";
        Curso curso = cursoRepository.findByNome(nomeCurso);
        Assertions.assertNotNull(curso);
        Assertions.assertEquals(nomeCurso, curso.getNome());
    }

    @Test
    public void naoDeveCarregarUmCursoQueNaoEstejaCadastrado(){
        String nomeCurso = "c#";
        Curso curso = cursoRepository.findByNome(nomeCurso);
        Assertions.assertNull(curso);

    }
}
