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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmigoDAOTest {

    @Autowired
    private AmigoDAO amigoDAO;

    @Before
    public void before(){ amigoDAO.removerTodosAmigos(); }

    @Test
    public void testAmigoDAO() {
        Pessoa leonardo = new Pessoa(1, "Leonardo", "Medeiros");
        Pessoa robson = new Pessoa(2, "Robson", "Araujo");

        List<Pessoa> amigos = new ArrayList<>();
        amigos.add(leonardo);
        amigos.add(robson);

        for (Pessoa amigo : amigos) {
            amigoDAO.adicionarAmigo(amigo);
        }

        assertTrue("Amigos não foram persistidos", amigoDAO.existeAmigos());
        assertEquals(2, amigoDAO.obterNumeroAmigos());

        Pessoa robsonPersistido = amigoDAO.obterAmigoIndex(1);

        assertEquals(robson.getNome(), robsonPersistido.getNome());
        assertEquals(robson.getSobrenome(), robsonPersistido.getSobrenome());

        List<Pessoa> amigosPersistidos = amigoDAO.obterAmigos(0, 100);
        assertNotNull(amigos);
        assertFalse(amigos.isEmpty());
        for (Pessoa amigo: amigos) {
            assertTrue(amigosPersistidos.contains(amigo));
        }

        amigoDAO.removerAmigo(leonardo);
        assertEquals(1, amigoDAO.obterNumeroAmigos());
    }
}