package com.github.pedrobacchini.introspringbootredis.repository;

import com.github.pedrobacchini.introspringbootredis.domain.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public class AmigoDAO {

    private static final String AMIGO_CHAVE = "amigos";

    private RedisTemplate<String, Pessoa> redisTemplate;

    @Autowired
    public AmigoDAO(RedisTemplate<String, Pessoa> redisTemplate) { this.redisTemplate = redisTemplate; }

    public void adicionarAmigo(Pessoa pessoa) { redisTemplate.opsForList().rightPush(AMIGO_CHAVE, pessoa); }

    public boolean existeAmigos() { return redisTemplate.hasKey(AMIGO_CHAVE); }

    public long obterNumeroAmigos() { return redisTemplate.opsForList().size(AMIGO_CHAVE); }

    public Pessoa obterAmigoIndex(int index) { return redisTemplate.opsForList().index(AMIGO_CHAVE, index); }

    public List<Pessoa> obterAmigos(long inicio, long itens) { return redisTemplate.opsForList().range(AMIGO_CHAVE, inicio, itens); }

    public long removerAmigo(Pessoa pessoa) { return redisTemplate.opsForList().remove(AMIGO_CHAVE, 1, pessoa); }

    public boolean removerTodosAmigos() { return redisTemplate.delete(AMIGO_CHAVE); }
}
