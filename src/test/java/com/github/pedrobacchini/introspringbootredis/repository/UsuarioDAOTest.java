package com.github.pedrobacchini.introspringbootredis.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioDAOTest {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Before
    public void before(){ usuarioDAO.deletarUsuario(); }

    @Test
    public void testUsuarioDAO() {
        usuarioDAO.adicionarNomeUsuario("Pedro");
        assertEquals(usuarioDAO.obterNomeUsuario(), "Pedro");
        usuarioDAO.atualizarNomeUsuario("Marcos");
        assertEquals(usuarioDAO.obterNomeUsuario(), "Marcos");
    }
}