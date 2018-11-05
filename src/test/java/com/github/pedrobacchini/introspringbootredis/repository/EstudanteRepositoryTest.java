package com.github.pedrobacchini.introspringbootredis.repository;

import com.github.pedrobacchini.introspringbootredis.domain.Estudante;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstudanteRepositoryTest {

    @Autowired
    private EstudanteRepository estudanteRepository;

    @Before
    public void before() { estudanteRepository.deleteAll(); }

    @Test
    public void testEstudanteRepository() {
        Estudante pedro = new Estudante(UUID.randomUUID().toString(), "Pedro", Estudante.Sexo.MASCULINO, 1);
        Estudante maria = new Estudante(UUID.randomUUID().toString(), "Maria", Estudante.Sexo.FEMININO, 3);

        estudanteRepository.save(pedro);
        estudanteRepository.save(maria);

        Estudante estudanteSalvo = estudanteRepository.findById(pedro.getId()).get();
        assertEquals(pedro, estudanteSalvo);

        pedro.setGrau(3);
        estudanteRepository.save(pedro);
        estudanteSalvo = estudanteRepository.findById(pedro.getId()).get();
        assertEquals(pedro.getGrau(), estudanteSalvo.getGrau());

        List<Estudante> estudantes = new ArrayList<>();
        estudanteRepository.findAll().forEach(estudantes::add);
        assertEquals(2, estudantes.size());

        estudanteRepository.deleteById(maria.getId());
        estudanteSalvo = estudanteRepository.findById(maria.getId()).orElse(null);
        assertNull(estudanteSalvo);
    }
}