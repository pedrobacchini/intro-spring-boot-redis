package com.github.pedrobacchini.introspringbootredis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAO {

    private static final String CHAVE_USUARIO = "usuario";

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public UsuarioDAO(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void adicionarNomeUsuario(String nome) { stringRedisTemplate.opsForValue().setIfAbsent(CHAVE_USUARIO, nome); }
    public void atualizarNomeUsuario(String nome) { stringRedisTemplate.opsForValue().set(CHAVE_USUARIO, nome); }
    public String obterNomeUsuario() { return stringRedisTemplate.opsForValue().get(CHAVE_USUARIO); }
    public void deletarUsuario() { stringRedisTemplate.delete(CHAVE_USUARIO); }
}
