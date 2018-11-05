package com.github.pedrobacchini.introspringbootredis.repository;

import com.github.pedrobacchini.introspringbootredis.domain.Pessoa;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FamiliaDAOTest {

    @Autowired
    private FamiliaDAO familiaDAO;

    @Before
    public void before() { familiaDAO.removerTodosMembrosFamilia(); }

    @Test
    public void testFamiliaDAO() {
        Pessoa maria = new Pessoa(1, "Maria", "Henrique");
        Pessoa monara = new Pessoa(2, "Monara", "Carolina");

        familiaDAO.adicionarMembrosFamilia(maria, monara);

        assertTrue("Familia não foi persistida", familiaDAO.existeFamilia());
        assertEquals(2, familiaDAO.obterNumeroMembrosFamilia());

        Set<Pessoa> familiares = familiaDAO.obterMembrosFamilia();
        assertNotNull(familiares);
        assertFalse(familiares.isEmpty());
        assertTrue(familiares.contains(maria));
        assertTrue(familiares.contains(monara));

        Pessoa paulo = new Pessoa(3, "Paulo", "Henrique");
        familiaDAO.adicionarMembrosFamilia(paulo);
        familiaDAO.removerMembrosFamilia(paulo);
        assertEquals(2, familiaDAO.obterNumeroMembrosFamilia());
    }
}