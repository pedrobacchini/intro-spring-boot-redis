package com.github.pedrobacchini.introspringbootredis.repository;

import com.github.pedrobacchini.introspringbootredis.domain.Pessoa;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpregadosDAOTest {

    @Autowired
    private EmpregadosDAO empregadosDAO;

    @Before
    public void before() { empregadosDAO.deletarTodosEmpregados(); }

    @Test
    public void testEmpregadosDAO() {
        Pessoa leonardo = new Pessoa(1, "Leonardo", "Medeiros");
        Pessoa robson = new Pessoa(2, "Robson", "Araujo");

        List<Pessoa> empregados = new ArrayList<>();
        empregados.add(leonardo);
        empregados.add(robson);

        for (Pessoa empregado: empregados) {
            empregadosDAO.adicionarEmpregado(empregado);
        }

        assertTrue("Empregados não foi persistido", empregadosDAO.existeEmoregados());
        assertEquals(2, empregadosDAO.obterNumeroEmpregados());

        leonardo.setSobrenome("Mendes");
        empregadosDAO.atualizarEmpregado(leonardo);
        assertEquals(leonardo.getSobrenome(), empregadosDAO.obterEmpregado(leonardo.getId()).getSobrenome());

        Map<Integer, Pessoa> empregadosPersistidos = empregadosDAO.obterTodosEmpregados();
        assertNotNull(empregadosPersistidos);
        assertFalse(empregadosPersistidos.isEmpty());
        for (Pessoa empregado: empregados){
            assertEquals(empregadosPersistidos.get(empregado.getId()), empregado);
        }

        empregadosDAO.deletarEmpregados(robson.getId());
        assertEquals(1, empregadosDAO.obterNumeroEmpregados());
    }
}