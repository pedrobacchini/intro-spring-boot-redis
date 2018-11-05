package com.github.pedrobacchini.introspringbootredis.repository;

import com.github.pedrobacchini.introspringbootredis.domain.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class FamiliaDAO {

    private static final String FAMILIA_CHAVE = "familia";

    private RedisTemplate<String, Pessoa> redisTemplate;

    @Autowired
    public FamiliaDAO(RedisTemplate<String, Pessoa> redisTemplate) { this.redisTemplate = redisTemplate; }

    public void adicionarMembrosFamilia(Pessoa... pessoas) { redisTemplate.opsForSet().add(FAMILIA_CHAVE, pessoas); }

    public boolean existeFamilia() { return redisTemplate.hasKey(FAMILIA_CHAVE); }

    public long obterNumeroMembrosFamilia() { return redisTemplate.opsForSet().size(FAMILIA_CHAVE); }

    public Set<Pessoa> obterMembrosFamilia() { return redisTemplate.opsForSet().members(FAMILIA_CHAVE); }

    public long removerMembrosFamilia(Pessoa... pessoas) { return redisTemplate.opsForSet().remove(FAMILIA_CHAVE, (Object[]) pessoas); }

    public boolean removerTodosMembrosFamilia() { return redisTemplate.delete(FAMILIA_CHAVE); }
}
